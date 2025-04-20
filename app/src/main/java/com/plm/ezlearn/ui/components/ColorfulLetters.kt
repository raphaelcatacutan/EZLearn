package com.plm.ezlearn.ui.components

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.semantics.invisibleToUser
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.plm.ezlearn.ui.theme.shootingStarFont
import kotlin.math.absoluteValue
import kotlin.random.Random

@Composable
fun ComponentOutlinedText(
    text: String,
    modifier: Modifier = Modifier,
    fillColor: Color = Color.Unspecified,
    outlineColor: Color,
    fontSize: TextUnit = TextUnit.Unspecified,
    fontStyle: FontStyle? = null,
    fontWeight: FontWeight? = null,
    fontFamily: FontFamily? = null,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    onTextLayout: (TextLayoutResult) -> Unit = {},
    style: TextStyle = LocalTextStyle.current,
    outlineDrawStyle: Stroke = Stroke(),
    randomizeColor: Boolean = false
) {
    Row(modifier = modifier) {
        text.forEach { char ->
            val charStr = char.toString()


            fun hsvToColor(h: Float, s: Float, v: Float): Color {
                val c = v * s
                val x = c * (1 - ((h / 60) % 2 - 1).absoluteValue)
                val m = v - c

                val (r1, g1, b1) = when {
                    h < 60 -> Triple(c, x, 0f)
                    h < 120 -> Triple(x, c, 0f)
                    h < 180 -> Triple(0f, c, x)
                    h < 240 -> Triple(0f, x, c)
                    h < 300 -> Triple(x, 0f, c)
                    else -> Triple(c, 0f, x)
                }

                return Color(r1 + m, g1 + m, b1 + m)
            }
            fun generateBrightColor(): Color {
                val hue = Random.nextFloat() * 360f
                val saturation = 0.9f + Random.nextFloat() * 0.1f   // 0.9 to 1.0
                val value = 0.9f + Random.nextFloat() * 0.1f        // 0.9 to 1.0
                return hsvToColor(hue, saturation, value)
            }

            // generate new color for each character if randomizeColor is true
            val fillColor = remember { if (randomizeColor) generateBrightColor() else fillColor }

            Box {
                Text(
                    text = charStr,
                    modifier = Modifier.semantics { invisibleToUser() },
                    color = outlineColor,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    letterSpacing = letterSpacing,
                    textDecoration = null,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                    minLines = minLines,
                    onTextLayout = onTextLayout,
                    style = style.copy(
                        shadow = null,
                        drawStyle = outlineDrawStyle,
                    ),
                )

                Text(
                    text = charStr,
                    color = fillColor,
                    fontSize = fontSize,
                    fontStyle = fontStyle,
                    fontWeight = fontWeight,
                    fontFamily = fontFamily,
                    letterSpacing = letterSpacing,
                    textDecoration = textDecoration,
                    textAlign = textAlign,
                    lineHeight = lineHeight,
                    overflow = overflow,
                    softWrap = softWrap,
                    maxLines = maxLines,
                    minLines = minLines,
                    onTextLayout = onTextLayout,
                    style = style,
                )
            }
        }
    }
}

