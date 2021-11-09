package edu.gatech.cog.ipglasses.renderingmethods

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import edu.gatech.cog.ipglasses.CaptionMessage
import edu.gatech.cog.ipglasses.CaptioningViewModel
import edu.gatech.cog.ipglasses.Renderers
import edu.gatech.cog.ipglasses.Speakers
import edu.gatech.cog.ipglasses.ui.theme.IPGlassesTheme


private const val TAG = "GlobalOnlyRenderer"

@Preview(showBackground = false, widthDp = 480, heightDp = 480)
@Composable
fun GlobalOnlyPreview() {
    val viewModel = CaptioningViewModel()
    viewModel.renderingMethodToUse = Renderers.GLOBAL_ONLY
    val lipsum = LoremIpsum(1)
    for ((i, chunk) in lipsum.values.take(4).iterator().withIndex()) {
        viewModel.addMessage(
            CaptionMessage(
                messageId = i,
                chunkId = 0,
                text = chunk,
                speakerId = "",
                focusedId = ""
            )
        )
    }

    IPGlassesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            GlobalOnlyRenderer(viewModel)
        }
    }
}

/**
 * Renders all spoken text.
 * @param viewModel: The [CaptioningViewModel] to use as a single source of truth for captions.
 */
@Composable
fun GlobalOnlyRenderer(viewModel: CaptioningViewModel) {
    val globalCaptionMessages = viewModel.globalCaptionMessages.value
    val textToDisplay = globalCaptionMessages.joinToString(" ") { message -> message.text }
    Box(
        modifier = Modifier
            .padding(30.dp)
            .fillMaxSize()
    ) {
        LimitedText(
            modifier = Modifier.align(Alignment.BottomStart),
            maxBottomLines = 100,
            fontSize = 28.sp,
            text = textToDisplay,
            color = Color.White,
        )
    }
}