package com.oncard

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.oncard.ui.theme.BottonCardShape
import com.oncard.ui.theme.ColorGreen
import com.oncard.ui.theme.ColorYellow
import com.oncard.ui.theme.OnboardTheme
import com.oncard.ui.theme.Poppins
import com.example.onboard.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            OnboardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize()

                ) {
                    val items = ArrayList<OnBoardingData>()
                    items.add(
                        OnBoardingData(
                            R.drawable.nimyears,
                            backgroundColor = Color(0xFF0189c5),
                            mainColor = Color(0xFF00B5EA),
                            mainText = "我",
                            subText = "Hi Nimyears..."

                        )
                    )

                    items.add(
                        OnBoardingData(
                            R.drawable.coffee,
                            backgroundColor = Color(0xFFE4AF19),
                            mainColor = ColorYellow,
                            mainText = "咖啡",
                            subText = "喝咖啡是一种生活..."
                        )
                    )

                    items.add(
                        OnBoardingData(
                            R.drawable.cooking,
                            backgroundColor = Color(0xFF96E172),
                            mainColor = ColorGreen,
                            mainText = "西餐",
                            subText = "真好吃..."
                        )
                    )

                    val pagerState = rememberPagerState(
                        pageCount = items.size,
                        initialOffscreenLimit = 2,
                        infiniteLoop = false,
                        initialPage = 0
                    )

                    OnBoardingPager(
                        item = items,
                        pagerState = pagerState,
                        modifier = Modifier.fillMaxWidth()
                    )

                }
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun OnBoardingPager(
    item: List<OnBoardingData>,
    pagerState: PagerState,
    modifier: Modifier = Modifier
){

    Box(modifier = modifier){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(state = pagerState) {page ->
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(item[page].backgroundColor),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    Image(painter = painterResource(id = item[page].image),
                        contentDescription = "",
                        modifier = Modifier.fillMaxWidth()
                    )

                }

            }

        }

        Box(modifier = Modifier.align(Alignment.BottomCenter)){
            Card(
                backgroundColor = Color.White,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(340.dp),
                elevation = 0.dp,
                shape = BottonCardShape.large
            ) {
                Box{
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        PagerIndicator(items = item,currentPage = pagerState.currentPage)
                        Text(
                            text = item[pagerState.currentPage].mainText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, end = 30.dp),
                            color = item[pagerState.currentPage].mainColor,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Right,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.ExtraBold
                        )

                        Text(
                            text = item[pagerState.currentPage].subText,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp, start = 30.dp, end = 20.dp),
                            color = Color.Gray,
                            fontFamily = Poppins,
                            textAlign = TextAlign.Center,
                            fontSize = 17.sp,
                            fontWeight = FontWeight.ExtraLight
                        )

                    }

                    Box(
                        modifier = Modifier
                            .align(Alignment.BottomCenter)
                            .padding(30.dp)
                    ){
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            if(pagerState.currentPage != 2) {
                                TextButton(onClick = { }) {
                                    Text(
                                        text = "",
                                        color = Color(0xFF292D32),
                                        fontFamily = Poppins,
                                        textAlign = TextAlign.Right,
                                        fontSize = 14.sp,
                                        fontWeight = FontWeight.SemiBold
                                    )

                                }

                                OutlinedButton(
                                    onClick = { },
                                    border = BorderStroke(
                                        14.dp,
                                        item[pagerState.currentPage].mainColor
                                    ),
                                    shape = RoundedCornerShape(50),
                                    colors = ButtonDefaults.outlinedButtonColors(
                                        contentColor = item[pagerState.currentPage].mainColor
                                    ),
                                    modifier = Modifier.size(65.dp)
                                ) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_right_arrow),
                                        contentDescription = "",
                                        modifier = Modifier.size(20.dp)
                                    )

                                }
                            }
                            else{

                                val exit = LocalContext.current as Activity

                                Button(onClick = { exit.finish() },
                                    colors = ButtonDefaults.buttonColors(
                                        backgroundColor = item[pagerState.currentPage].mainColor
                                    ),
                                    contentPadding = PaddingValues(vertical = 12.dp),
                                    elevation = ButtonDefaults.elevation(
                                        defaultElevation = 0.dp
                                    ),
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "结束啦，谢谢观看",
                                        color = Color.White,
                                        fontSize = 16.sp
                                    )

                                }
                            }

                        }
                    }
                }

            }
        }
    }


}

@Composable
fun PagerIndicator(items: List<OnBoardingData>, currentPage: Int) {
    Row (
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier.padding(top = 20.dp)
            ){
        repeat(items.size){
            Indicator(
                isSelected = it == currentPage,
                color = items[it].mainColor
            )
        }

    }

}

@Composable
fun Indicator(isSelected:Boolean,color: Color){
    val width = animateDpAsState(targetValue = if (isSelected) 40.dp else 10.dp)
    Box(
        modifier = Modifier
            .padding(4.dp)
            .height(10.dp)
            .width(width.value)
            .clip(CircleShape)
            .background(
                if (isSelected) color else Color.Gray.copy(alpha = 0.5f)
            )
    )

}


