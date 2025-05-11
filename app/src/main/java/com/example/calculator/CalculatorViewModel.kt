package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow


class CalculatorViewModel : ViewModel() {


    private val _state = MutableStateFlow(Display(expression = "45x8", result = "360"))
    val state = _state.asStateFlow()
    fun processCommand(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when (command) {
            CalculatorCommand.Clear -> {
                _state.value = Display("", "")
            }

            CalculatorCommand.Evaluate -> {

            }

            is CalculatorCommand.Input -> {

            }
        }
    }

//    fun processUserInput(input: String) {
//        when (input) {
//            "AC" -> state.value = Display("", "")
//        }
//    }
}

sealed interface CalculatorCommand {
    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand
}

enum class Symbol {
    DIGIT_0,
    DIGIT_1,
    DIGIT_2,
    DIGIT_3,
    DIGIT_4,
    DIGIT_5,
    DIGIT_6,
    DIGIT_7,
    DIGIT_8,
    DIGIT_9,
    ADD,
    SUBTRACT,
    MULTIPLY,
    DIVIDE,
    PERCENT,
    DOT,
    PI,
    POWER,
    FACTORIAL,
    SQRT,
    PARENTHESIS
}

data class Display(
    val expression: String,
    val result: String
)