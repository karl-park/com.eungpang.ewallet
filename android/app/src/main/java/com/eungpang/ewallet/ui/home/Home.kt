package com.eungpang.ewallet.ui.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eungpang.ewallet.domain.model.Card
import com.eungpang.ewallet.domain.model.Transaction
import com.eungpang.ewallet.ui.MainViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@Composable
fun Home(
    viewModel: MainViewModel = viewModel(),
) {
    val isRefreshing by viewModel.isRefreshingHome.collectAsState()

    val cards by viewModel.cards.collectAsState()
    val transactions by viewModel.transactions.collectAsState()

    SwipeRefresh(
        state = rememberSwipeRefreshState(isRefreshing),
        onRefresh = { viewModel.loadHomeScreen() }
    ) {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
        ) {
            item {
                CardSection(
                    modifier = Modifier
                        .fillMaxWidth(),
                    cards = cards,
                    onCardClick = {

                    },
                    onAddCardClick = {

                    }
                )
            }

            TransactionSectionItems(
                transactions = transactions,
                onShowMoreClick = {

                },
                onTransactionClick = {

                }
            )
        }
    }
}

@Composable
fun CardSection(
    modifier: Modifier = Modifier,
    cards: List<Card>,
    onCardClick: (Card) -> Unit,
    onAddCardClick: () -> Unit
) {
    Column {
        Text(
            text = "Cards",
            style = MaterialTheme.typography.h3
        )

        LazyRow {
            items(
                count = cards.size,
                key = { idx ->
                    cards[idx].numbers + cards[idx].holderName + cards[idx].validDateText
                }
            ) { idx ->
                CardItem(
                    card = cards[idx],
                    onCardClick = onCardClick
                )

                Spacer(
                    modifier = Modifier
                        .width(12.dp)
                )
            }

            item {
                CardAddItem {
                    onAddCardClick()
                }
            }
        }
    }
}

@Composable
fun CardItem(
    modifier: Modifier = Modifier,
    card: Card,
    onCardClick: (Card) -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
    ) {
        Card(
            modifier = Modifier
                .size(300.dp, 200.dp)
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
                .clickable { onCardClick(card) }
        ) {
            Column {
                Text(text = card.cardName)
                Text(text = card.numbers)
                Row {
                    Text(text = card.validDateText)
                    Spacer(modifier = Modifier.weight(1f))
                    Text(text = card.holderName)
                }

            }
        }
    }
}

@Composable
fun CardAddItem(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Surface(
        modifier = modifier,
        shape = RoundedCornerShape(4.dp),
    ) {
        Card(
            modifier = Modifier
                .size(300.dp, 200.dp)
                .background(MaterialTheme.colors.secondary)
                .padding(16.dp)
                .clickable { onClick() }
        ) {
            Box {
                Icon(
                    imageVector = Icons.Default.PlusOne,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.Center)
                )
            }
        }
    }
}

fun LazyListScope.TransactionSectionItems(
    transactions: List<Transaction>,
    onShowMoreClick: () -> Unit,
    onTransactionClick: (Transaction) -> Unit
) {
    item {
        Surface(
            modifier = Modifier.padding(
                top = 26.dp,
                bottom = 10.dp
            )
        ) {
            Text(
                text = "Transactions",
                style = MaterialTheme.typography.h3
            )
        }
    }

    items(
        count = transactions.size,
        key = { idx -> transactions[idx].txId },
    ) {
        val transaction = transactions[it]
        TransactionItem(
            modifier = Modifier.fillMaxWidth(),
            transaction = transaction
        ) {
            onTransactionClick(transaction)
        }
    }

    item {
        Text(
            modifier = Modifier.clickable { onShowMoreClick() },
            text = "Show More",
            style = MaterialTheme.typography.h6
        )
    }
}

@Composable
fun TransactionItem(
    modifier: Modifier = Modifier,
    transaction: Transaction,
    onItemClick: (Transaction) -> Unit,
) {
    Row(
        modifier = modifier
            .padding(vertical = 10.dp, horizontal = 16.dp)
            .clickable { onItemClick(transaction) }
    ) {
        Surface(
            shape = RoundedCornerShape(4.dp),
            modifier = Modifier
                .size(100.dp, 80.dp)
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Green)
            ) { }
        }

        Column {
            Text(text = transaction.bank)
            Text(text = transaction.description)
        }

        Spacer(modifier = Modifier.weight(1f))

        val annotatedText = buildAnnotatedString {
            when {
                transaction.money < 0 -> {
                    withStyle(
                        style = SpanStyle(color = Color.Blue)
                    ) {
                        append(transaction.signText)
                        append(" ")
                        append(transaction.moneyText)
                    }
                }
                transaction.money > 0 -> {
                    withStyle(
                        style = SpanStyle(color = Color.Red)
                    ) {
                        append(transaction.signText)
                        append(" ")
                        append(transaction.moneyText)
                    }
                }
                else -> {
                    withStyle(
                        style = SpanStyle(color = Color.Black)
                    ) {
                        append(transaction.moneyText)
                    }
                }
            }
        }

        Column {
            Text(text = annotatedText)
        }
    }
}
