package com.example.shopkmm01.presentation.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.painter.BitmapPainter
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.shopkmm01.business.domain.main.Product
import com.example.shopkmm01.presentation.ui.components.DefaultScreenUI
import com.example.shopkmm01.presentation.ui.screen_navigation.Navigation
import com.example.shopkmm01.presentation.ui.screen_navigation.ScreensState
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.grey_300
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.grey_500
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.grey_700
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.primary
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.primaryVariant
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors.splashBackground
import com.seiko.imageloader.ImageLoader
import com.seiko.imageloader.rememberAsyncImagePainter


@Composable
internal fun SplashScreen(
    navigationState: MutableState<ScreensState>,
    viewModel: SplashViewModel
) {
    val state = viewModel.state.collectAsState()

    SplashScreenView(
        state = state,
        navigateToMainScreen = {
            navigationState.value = ScreensState(navigation = Navigation.MainNavigation)
        },
        events = viewModel::onTriggerEvent
    )
}

@Composable
internal fun SplashScreenView(
    state: State<SplashState>,
    events: (SplashEvent) -> Unit,
    navigateToMainScreen: () -> Unit,
) {
    if (state.value.readyToNavigate) navigateToMainScreen()


    DefaultScreenUI(
        queue = state.value.errorQueue,
        onRemoveHeadFromQueue = {
            events(SplashEvent.OnRemoveHeadFromQueue)
        },
        progressBarState = state.value.progressBarState,
    ) {

        Box(
            modifier = Modifier.fillMaxSize().background(color = splashBackground),
            contentAlignment = Alignment.BottomCenter
        ) {
            Box(
                modifier = Modifier.fillMaxSize().offset(y = 450.dp)
                    .background(
                        Brush.radialGradient(
                            radius = 800f, colors = listOf<Color>(
                                primary,
                                primary,
                                primary.copy(alpha = 0.3f),
                                primary.copy(alpha = 0.0f),
                            )
                        )
                    )
            )

            Box(
                modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.BottomCenter
            ) {

                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceBetween
                ) {

                    Column(
                        modifier = Modifier.fillMaxWidth().height(450.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        Text(
                            "NIKE",
                            style = MaterialTheme.typography.h1.copy(
                                color = grey_700,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            "H&M",
                            style = MaterialTheme.typography.h1.copy(
                                color = grey_700,
                                fontWeight = FontWeight.Bold
                            )
                        )
                        Text(
                            "GUCCI",
                            style = MaterialTheme.typography.h1.copy(
                                color = grey_700,
                                fontWeight = FontWeight.Bold
                            )
                        )
                    }

                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            "LIVE YOUR\nPERFECT",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.h3.copy(color = Color.White),
                        )
                        Spacer(modifier = Modifier.size(16.dp))
                        Text(
                            "Smart, gorgeous & fashionable\ncollection make you cool",
                            textAlign = TextAlign.Center,
                            style = MaterialTheme.typography.subtitle1.copy(color = grey_300),
                        )
                    }


                    Text(
                        "Getting Start...",
                        style = MaterialTheme.typography.subtitle1.copy(color = grey_300),
                    )

                }


            }
        }
    }
}


