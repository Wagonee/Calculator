package com.example.calculator

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun Calculator(
    modifier: Modifier = Modifier
) {

    val viewModel: CalculatorViewModel = viewModel()
    val state = viewModel.state.collectAsState()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 0.dp, topEnd = 0.dp, 40.dp, 40.dp))
                .background(MaterialTheme.colorScheme.primaryContainer)
                .weight(1f)
                .padding(bottom = 16.dp, end = 40.dp, start = 40.dp),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.End
        ) {
            when (val currentState = state.value) {

                is CalculatorState.Error -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.error
                    )
                    Text(
                        text = "",
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
                CalculatorState.Initial -> {}
                is CalculatorState.Input -> {
                    Text(
                        text = currentState.expression,
                        lineHeight = 36.sp,
                        fontSize = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = currentState.result,
                        lineHeight = 17.sp,
                        fontSize = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }

                is CalculatorState.Success -> {
                    Text(
                        text = currentState.result,
                        fontSize = 36.sp,
                        lineHeight = 36.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onPrimaryContainer
                    )
                    Text(
                        text = "",
                        fontSize = 17.sp,
                        lineHeight = 17.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = MaterialTheme.colorScheme.onSecondaryContainer
                    )
                }
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SQRT))
                    },
                text = "√",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,

                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PI))
                    },
                text = "π",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,

                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center

            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.POWER))
                    },
                text = "^",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center
            )
            Text(
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.FACTORIAL))
                    },
                text = "!",
                fontSize = 30.sp,
                fontWeight = FontWeight.SemiBold,
                color = MaterialTheme.colorScheme.onBackground,
                textAlign = TextAlign.Center

            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Clear)
                    }
                    .background(MaterialTheme.colorScheme.tertiary)
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "AC",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier

                    .weight(1f)

                    .clip(CircleShape)

                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PARENTHESIS))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "( )",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.PERCENT))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "%",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.ADD))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "+",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_7))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "7",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_8))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "8",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_9))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "9",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.MULTIPLY))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "X",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_4))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "4",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_5))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "5",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_6))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "6",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.SUBTRACT))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "-",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_1))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "1",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_2))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "2",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }

            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_3))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "3",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .weight(1f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIVIDE))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "/",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(
                modifier = Modifier
                    .weight(2f)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DIGIT_0))
                    }
                    .aspectRatio(2f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "0",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }


            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Input(Symbol.DOT))
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = ",",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .weight(1f)
                    .background(MaterialTheme.colorScheme.secondary)
                    .clickable {
                        viewModel.processCommand(CalculatorCommand.Evaluate)
                    }
                    .aspectRatio(1 / 1f),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "=",
                    fontSize = 40.sp,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
        }

    }
}



