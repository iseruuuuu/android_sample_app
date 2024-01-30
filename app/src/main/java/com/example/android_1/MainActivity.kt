package com.example.android_1

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Checkbox
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//dpを追加した
import androidx.compose.ui.unit.dp
import com.example.android_1.ui.theme.Android_1Theme

//Fragment, Activityの２つがある。
//Jetpack Composeは、基本的にComponentActivityを使う

class MainActivity : ComponentActivity() {
    //flutterでいうinitState
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //kotlinでは、setContentViewになっている。
        setContent {
            Android_1Theme {
                //FlutterでいうScaffold
                Surface(
                    //変更の編集
                    //FlutterでいうとContainerの要素
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Column(
                        //ここでmainAxisAlimentとかが入る。
                        verticalArrangement = Arrangement.SpaceAround,
                        horizontalAlignment = Alignment.CenterHorizontally,

                        ) {
                        Text(text = "Hello Android!")
                        Row {
                            Greeting("Android")
                            Text(text = "Hello Android!")
                        }
                        //FlutterでいうContainer
                        Box {

                            Text(
                                text = "Hello Android!",
                                color = MaterialTheme.colors.primary,
                            )
                        }
                        MessageList(
                            //Listの引数の書き方
                            messages = listOf(
                                "1",
                                "2",
                            )
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Padding $name!",
    modifier = Modifier.padding(all = 18.dp)
        )
}

@Composable
fun ConversationScreen() {
    //viewModelは、messagesのLiveDataを公開している
//    val viewModel: ConversationViewModel = viewModel();
    //新しいデータが届くたびに再コンポーズされるようになる
    //状態が変化した時に、対象の関数が自動的に再実行される。
    //入力が変化した@Composableのみを再実行する
//    val messages by viewModel.message.observeAsState();
//    MessageList(messages)
}

@Composable
fun MessageList(messages: List<String>) {
    val checkedState = remember {
        mutableStateOf(true)
    }
    Column() {
        if (messages.isEmpty()) {
            Text("No messages")
        } else {
            messages.forEach { messages ->
                Text(text = messages)
            }
        }
        //checkBoxの状態変化
        Checkbox(
            checked = checkedState.value,
            onCheckedChange = { checkedState.value = it }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Android_1Theme {
        Greeting("Android")
    }
}