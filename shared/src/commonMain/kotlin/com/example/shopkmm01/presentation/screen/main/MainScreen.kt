package com.example.shopkmm01.presentation.screen.main

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.style.TextOverflow.Companion.Ellipsis
import androidx.compose.ui.unit.dp
import com.example.shopkmm01.business.domain.main.Category
import com.example.shopkmm01.business.domain.main.Product
import com.example.shopkmm01.presentation.ui.components.DefaultScreenUI
import com.example.shopkmm01.presentation.ui.screen_navigation.Navigation
import com.example.shopkmm01.presentation.ui.screen_navigation.ScreensState
import com.example.shopkmm01.presentation.ui.theme.style.ShopKmm01AppColors
import com.seiko.imageloader.rememberAsyncImagePainter


@Composable
internal fun MainScreen(
    navigationState: MutableState<ScreensState>,
    viewModel: MainViewModel
) {
    val state = viewModel.state.collectAsState()
    MainScreenView(
        state = state,
        navigateToDetailScreen = {
            navigationState.value = ScreensState(navigation = Navigation.DetailNavigation(it))
        },
        events = viewModel::onTriggerEvent
    )
}

@Composable
internal fun MainScreenView(
    state: State<MainState>,
    events: (MainEvent) -> Unit,
    navigateToDetailScreen: (Product) -> Unit,
) {
    val url =
        "https://t4.ftcdn.net/jpg/03/20/46/13/360_F_320461388_5Snqf6f2tRIqiWlaIzNWrCUm1Ocaqhfm.jpg"
    val painter = rememberAsyncImagePainter(url)

    DefaultScreenUI(
        queue = state.value.errorQueue,
        onRemoveHeadFromQueue = {
            events(MainEvent.OnRemoveHeadFromQueue)
        },
        progressBarState = state.value.progressBarState,
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(top = 16.dp, start = 16.dp, end = 16.dp)) {

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Icon(
                    imageVector = Icons.Default.Menu,
                    contentDescription = null,
                    tint = ShopKmm01AppColors.grey_700,
                    modifier = Modifier.size(26.dp)
                )
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = null,
                    tint = ShopKmm01AppColors.grey_700,
                    modifier = Modifier.size(26.dp)
                )
            }

            Spacer(modifier = Modifier.size(24.dp))

            Card(
                modifier = Modifier.fillMaxWidth().height(200.dp),
                shape = RoundedCornerShape(32.dp),
                elevation = 0.dp
            ) {

                Image(
                    painter = painter,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.size(24.dp))

            LazyRow(contentPadding = PaddingValues(0.dp) ) {
                items(items = state.value.categories) { category ->
                    CategoryChips(
                        category = category,
                        state.value.selectedCategory.category == category.category
                    ) { events(MainEvent.UpdateSelectedCategory(category)) }
                }
            }

            Spacer(modifier = Modifier.size(24.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    "New Items",
                    style = MaterialTheme.typography.h5,
                )
                Text(
                    "See All",
                    style = MaterialTheme.typography.body1.copy(
                        color = ShopKmm01AppColors.grey_500,
                        fontWeight = FontWeight.W600
                    ),
                )
            }

            Spacer(modifier = Modifier.size(16.dp))

            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
            ) {
                items(state.value.products) { product ->
                    ProductCard(product) {
                        navigateToDetailScreen(it)
                    }
                }
            }
        }
    }
}

@Composable
fun ProductCard(
    product: Product,
    navigateToDetailScreen: (Product) -> Unit,
) {

    Card(
        modifier = Modifier.padding(4.dp).clickable { navigateToDetailScreen(product) },
        shape = RoundedCornerShape(24.dp),
        elevation = 0.dp,
    ) {
        Column(
            modifier = Modifier,
            horizontalAlignment = Alignment.End,
            verticalArrangement = Arrangement.SpaceBetween
        ) {


            val painter = rememberAsyncImagePainter(product.image)
            Image(
                painter = painter,
                contentDescription = null,
                contentScale = ContentScale.Fit,
                modifier = Modifier.fillMaxWidth().height(150.dp)
                    .padding(4.dp),
            )

            Spacer(modifier = Modifier.size(8.dp))

            Text(
                product.title,
                maxLines = 1,
                textAlign = TextAlign.Start,
                style = MaterialTheme.typography.caption.copy(color = ShopKmm01AppColors.primary),
                modifier = Modifier.padding(4.dp),
            )

            Text(
                product.description,
                maxLines = 2,
                style = MaterialTheme.typography.caption,
                textAlign = TextAlign.Start,
                modifier = Modifier.padding(4.dp),
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    "$${product.price}",
                    style = MaterialTheme.typography.h6,
                    modifier = Modifier.wrapContentSize().padding(horizontal = 4.dp),
                )
                Card(
                    modifier = Modifier.size(35.dp),
                    shape = RoundedCornerShape(
                        topStart = 16.dp,
                        topEnd = 0.dp,
                        bottomEnd = 0.dp,
                        bottomStart = 0.dp
                    ),
                    backgroundColor = Color.Black,
                    elevation = 0.dp
                ) {
                    Icon(
                        Icons.Default.Add, null, tint = Color.White,
                        modifier = Modifier.padding(4.dp),
                    )
                }

            }


        }
    }

}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun CategoryChips(category: Category, isSelected: Boolean, onItemSelected: (Category) -> Unit) {
    Surface(modifier = Modifier.background(Color.Transparent).height(56.dp).padding(4.dp),
        shape = RoundedCornerShape(30.dp),
        color = if (isSelected) ShopKmm01AppColors.primaryVariant else Color.White,
        onClick = { onItemSelected(category) }) {
        Box(contentAlignment = Alignment.Center) {
            Text(
                category.category,
                style = MaterialTheme.typography.body1.copy(color = if (isSelected) Color.White else ShopKmm01AppColors.grey_500),
                modifier = Modifier.padding(start = 24.dp, top = 8.dp, bottom = 8.dp, end = 24.dp)
                    .background(Color.Transparent),
            )
        }
    }
}
