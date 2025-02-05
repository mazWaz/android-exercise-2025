package com.example.myapplication.ui.detail.component

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import coil3.request.ImageRequest
import com.example.myapplication.movie_detail.domain.models.Cast
import com.example.myapplication.utils.K

@Composable
fun ActorItem(
    modifier: Modifier = Modifier,
    cast: Cast
) {
    val imgRequest = ImageRequest.Builder(LocalContext.current)
        .data("${K.BASE_IMAGE_URL}${cast.profilePath}")
        .crossfade(true)
        .build()
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AsyncImage(
            model = imgRequest,
            contentDescription = null, // decorative element
            modifier = Modifier
                .size(48.dp)
                .clip(CircleShape),
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            },
            placeholder = painterResource(id = R.drawable.baseline_person_24)
        )
        Text(text = cast.genderRole, style = MaterialTheme.typography.bodySmall)
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = cast.firstName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )
        Text(
            text = cast.lastName,
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.Bold
        )

    }


}