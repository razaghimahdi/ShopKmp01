package com.example.shopkmm01.presentation.screen.details

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.*
import com.example.shopkmm01.business.domain.main.Product
import com.example.shopkmm01.business.domain.main.Rating
import com.example.shopkmm01.presentation.screen.main.MainEvent
import com.example.shopkmm01.presentation.ui.components.DefaultScreenUI
import com.example.shopkmm01.presentation.ui.screen_navigation.Navigation
import com.example.shopkmm01.presentation.ui.screen_navigation.ScreensState
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors
import com.seiko.imageloader.rememberAsyncImagePainter


@OptIn(ExperimentalUnitApi::class)
@Composable
internal fun DetailScreen(navigationState: MutableState<ScreensState>, product: Product) {
    val painter = rememberAsyncImagePainter(product.image)

    DefaultScreenUI(onRemoveHeadFromQueue = {}) {

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {


            Column(
                modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 16.dp, end = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = null,
                        tint = ShopKmm01AppColors.grey_700,
                        modifier = Modifier.size(26.dp).clickable(onClick = {
                            navigationState.value = ScreensState(
                                Navigation.MainNavigation
                            )
                        })
                    )
                    Text(
                        "Product Detail",
                        style = MaterialTheme.typography.h5
                    )
                    Icon(
                        imageVector = Icons.Default.ShoppingCart,
                        contentDescription = null,
                        tint = ShopKmm01AppColors.grey_700,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Spacer(modifier = Modifier.size(24.dp))

                Card(
                    modifier = Modifier.fillMaxWidth().height(350.dp),
                    shape = RoundedCornerShape(32.dp),
                    elevation = 0.dp
                ) {

                    Image(
                        painter = painter,
                        contentDescription = null,
                        contentScale = ContentScale.Fit,
                        modifier = Modifier.fillMaxSize().padding(6.dp)
                    )
                }

                Spacer(modifier = Modifier.size(16.dp))

                Text(
                    product.title,
                    style = MaterialTheme.typography.subtitle1.copy(color = ShopKmm01AppColors.primary),
                )

                Spacer(modifier = Modifier.size(8.dp))

                Rate(product.rating)


                Text(
                    product.description,
                    style = MaterialTheme.typography.caption,
                )


                Spacer(modifier = Modifier.size(8.dp))



                Text(
                    "Select color",
                    style = MaterialTheme.typography.subtitle2,
                )

                ColorRowList()


            }


            Column {
                Divider(color = ShopKmm01AppColors.grey_500)

                Row(
                    modifier = Modifier.fillMaxWidth().padding(16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {


                    Text(
                        "$${product.price}",
                        style = MaterialTheme.typography.h5,
                    )
                    Button(
                        {},
                        shape = RoundedCornerShape(12.dp),
                    ) {

                        Text(
                            "Add to Cart",
                            style = MaterialTheme.typography.button,
                            modifier = Modifier.padding(8.dp),
                        )
                    }
                }
            }


        }
    }
}

@Composable
internal fun ColorRowList() {
    Row(modifier = Modifier.fillMaxWidth()) {
        ColorCard(color = ShopKmm01AppColors.red_400,false)
        ColorCard(color = ShopKmm01AppColors.yellow_400,true)
        ColorCard(color = ShopKmm01AppColors.blue_400,false)
        ColorCard(color = ShopKmm01AppColors.green_400,false)
        ColorCard(color = ShopKmm01AppColors.cyan_400,false)
        ColorCard(color = ShopKmm01AppColors.orange_400,false)
    }
}

@Composable
fun ColorCard(color: Color, isSelected: Boolean) {
    Card(
        elevation = 0.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = color,
        modifier = Modifier.padding(2.dp).size(35.dp)
    ) {
        if (isSelected) {
            Icon(Icons.Default.Check,null, modifier = Modifier.padding(4.dp))
        }
    }
}


@Composable
internal fun Rate(rating: Rating) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Icon(
            imageVector = Icons.Filled.Star,
            contentDescription = null,
            tint = ShopKmm01AppColors.yellow_700,
        )
        Text(
            text = rating.rate.toString(),
            style = MaterialTheme.typography.subtitle1,
        )
        Text(
            text = "(${rating.count})",
            style = MaterialTheme.typography.caption.copy(color = ShopKmm01AppColors.grey_600),
        )
    }
}


