package com.eungpang.ewallet.ui.account

import android.widget.Toast
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.eungpang.ewallet.R
import com.eungpang.ewallet.domain.model.User
import com.eungpang.ewallet.ui.theme.KWalletTheme

@ExperimentalCoilApi
@Composable
fun Account(
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    Column {
        Spacer(Modifier.padding(top = 50.dp))
        AccountProfile(
            modifier = Modifier.fillMaxWidth(),
            user = User(
                name = "Karl Park",
                profileUrl = "https://avatars.githubusercontent.com/u/7299877?v=4",
                isPremium = true
            )
        ) {
            Toast
                .makeText(context, "AccountProfile", Toast.LENGTH_SHORT)
                .show()
        }

        Spacer(Modifier.padding(top = 50.dp))

        AccountItemRow(
            text = "Personal Information"
        ) {
            Toast
                .makeText(context, "Personal Information", Toast.LENGTH_SHORT)
                .show()
        }

        AccountItemRow(
            text = "Payment Preference"
        ) {
            Toast
                .makeText(context, "Payment Preference", Toast.LENGTH_SHORT)
                .show()
        }

        AccountItemRow(
            text = "About"
        ) {
            Toast
                .makeText(context, "About", Toast.LENGTH_SHORT)
                .show()
        }

        Spacer(Modifier.padding(top = 32.dp))

        Surface(
            modifier = Modifier
                .align(Alignment.CenterHorizontally),
            shape = RoundedCornerShape(4.dp)
        ) {
            TextButton(onClick = {
                Toast
                    .makeText(context, "Logout", Toast.LENGTH_SHORT)
                    .show()
            }) {
                Text("Logout")
            }
        }

        Spacer(Modifier.padding(top = 32.dp))
    }
}

@ExperimentalCoilApi
@Composable
fun AccountProfile(
    modifier: Modifier = Modifier,
    user: User?,
    onClick: () -> (Unit)
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            Modifier
                .size(80.dp)
                .clickable { onClick() }
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize(),
                shape = CircleShape
            ) {
                val imagePainter = if (user?.profileUrl.isNullOrEmpty()) {
                    rememberImagePainter(data = user?.profileUrl ?: "")
                } else {
                    painterResource(R.drawable.profile_placeholder)
                }

                Image(
                    painter = imagePainter,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize()
                )
            }

            if (user?.isPremium == true) {
                Surface(
                    modifier = Modifier
                        .size(24.dp)
                        .align(Alignment.BottomEnd),
                    shape = CircleShape
                ) {
                    Canvas(
                        modifier = Modifier.fillMaxSize(),
                        onDraw = {
                            drawCircle(Color.Red)
                        }
                    )
                }
            }
        }

        Spacer(Modifier.padding(top = 6.dp))

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = user?.name ?: "Anonymous"
        )
    }
}

@Composable
fun AccountItemRow(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> (Unit)
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onClick() },
    ) {
        Row(
            modifier = modifier
                .padding(vertical = 6.dp, horizontal = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                modifier = Modifier
//                    .padding(vertical = 6.dp, horizontal = 16.dp)
                    .weight(1f),
                text = text
            )

            Image(
                painter = painterResource(R.drawable.ic_baseline_chevron_right_24),
                contentDescription = null
            )
        }
    }

}

@ExperimentalCoilApi
@Preview
@Composable
fun AccountPreview() {
    KWalletTheme {
        Account()
    }
}