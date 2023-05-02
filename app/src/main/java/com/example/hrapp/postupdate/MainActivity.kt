package com.example.hrapp.postupdate

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.Data
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.hrapp.UserService
import com.example.hrapp.postupdate.model.DataModel
import com.example.hrapp.postupdate.ui.theme.HRAppTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
             HRAppTheme() { // on below line we are specifying background color for our application
                 Surface(
                     // on below line we are specifying modifier and color for our app
                     modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
                 ) {
                     Scaffold(
                         topBar = {
                             TopAppBar(backgroundColor = Color.Yellow, title = {
                                     Text(text = "Retrofit POST Request", modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, color = Color.Black
                                     )
                                 })
                         }) {
                         Column(modifier = Modifier.padding(it)) {
                             postData()
                         }
                     }
                 }
             }
        }
    }
}

@Composable
fun postData(){
    val ctx = LocalContext.current
    // capture users input according to model
    val username = remember{
        mutableStateOf(TextFieldValue())
    }
    val job = remember{
        mutableStateOf(TextFieldValue())
    }
    val result = remember {
        mutableStateOf("")
    }
    
    // design of the page 
    // form to post data
    Column(modifier = Modifier
        .fillMaxSize()
        .fillMaxHeight()
        .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Retrofit POST request", color = Color.Black, fontSize = 20.sp, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
        Spacer(modifier = Modifier.height(5.dp))
        TextField(value = username.value , onValueChange = {username.value = it}, singleLine = true, placeholder = { Text(
            text = "Enter Name"
        )}, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textStyle = TextStyle(color = Color.Black, fontSize = 15.sp))
        Spacer(modifier = Modifier.height(5.dp))
        TextField(value = job.value , onValueChange = {job.value = it}, singleLine = true, placeholder = { Text(
            text = "Enter Job"
        )}, modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth(), textStyle = TextStyle(color = Color.Black, fontSize = 15.sp))
        Spacer(modifier = Modifier.height(10.dp))
        Button(onClick = {
            posttoRetrofit(ctx,username,job,result)
        }) {
            Text("Post")
        }
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = result.value, color = Color.Black, fontWeight = FontWeight.Bold, textAlign = TextAlign.Center)
    }
}

private fun posttoRetrofit(ctx: Context, username: MutableState<TextFieldValue>, job: MutableState<TextFieldValue>, result: MutableState<String>) {
    // new base URL
    var url = "https://reqres.in/api/"
    // get the instance of the retrofit
    val retrofit = Retrofit.Builder().baseUrl(url).addConverterFactory(GsonConverterFactory.create()).build()
    // here we create a retrofit instance for our interface
    val userPost = retrofit.create(UserService::class.java)
    // use users input to create an instance of the data class
    val dataModel = DataModel(username.value.text,job.value.text)
    // calling the method create an update and pass in our model class
    val call: Call<DataModel?>? = userPost.postData(dataModel)
    //below we execute and manipulate correct response
    call!!.enqueue(object : Callback<DataModel?>{
        override fun onResponse(call: Call<DataModel?>, response: Response<DataModel?>) {
            Toast.makeText(ctx,"Data posted to API", Toast.LENGTH_SHORT).show()
            // get the actual response and pass it to the model class
            val model : DataModel? = response.body()
            // picking posted data from the model class
            val responseDetails  = "Response code : " + response.code() + "\n" + " User name " + model!!.name + "\n" + " Job " + model!!.job
            result.value = responseDetails
            //Toast.makeText(ctx,result.value, Toast.LENGTH_SHORT).show()
        }
        override fun onFailure(call: Call<DataModel?>, t: Throwable) {
           result.value = "Error found is " + t.message
            Toast.makeText(ctx,result.value, Toast.LENGTH_SHORT).show()
        }
    })
}


















