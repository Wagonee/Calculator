package com.example.calculator

import android.util.Log
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import org.mariuszgromada.math.mxparser.Expression


class CalculatorViewModel : ViewModel() {

    private var _expression = ""

    private val _state: MutableStateFlow<CalculatorState> =
        MutableStateFlow(CalculatorState.Initial)
    val state = _state.asStateFlow()
    fun processCommand(command: CalculatorCommand) {
        Log.d("CalculatorViewModel", "Command: $command")
        when (command) {
            CalculatorCommand.Clear -> {
                _expression = ""
                _state.value = CalculatorState.Initial
            }

            CalculatorCommand.Evaluate -> {
                val result = evaluate()
                if (result != null) {
                    _state.value = CalculatorState.Success(result = result)
                    _expression = result
                } else {
                    _state.value = CalculatorState.Error(expression = _expression)
                }
            }

            is CalculatorCommand.Input -> {


                val symbol = if (command.symbol != Symbol.PARENTHESIS) {
                    command.symbol.value
                } else {
                    getCorrectParenthesis()
                }
                _expression += symbol
                _state.value = CalculatorState.Input(
                    expression = _expression,
                    result = evaluate() ?: ""
                )
            }
        }
    }

    private fun getCorrectParenthesis(): String {
        val openCount = _expression.count { it == '(' }
        val closeCount = _expression.count { it == ')' }
        return when {
            _expression.isEmpty() -> "("
            !_expression.last()
                .isDigit() && _expression.last() != ')' && _expression.last() != 'π' -> "("

            openCount > closeCount -> ")"
            else -> "("
        }
    }


    private fun evaluate(): String? {
       return Expression(_expression.replace('x', '*').replace(',', '.')).calculate().takeIf { it.isFinite() } ?.toString()
    }
}

sealed interface CalculatorCommand {
    data object Clear : CalculatorCommand
    data object Evaluate : CalculatorCommand
    data class Input(val symbol: Symbol) : CalculatorCommand
}

enum class Symbol(val value: String) {
    DIGIT_0("0"),
    DIGIT_1("1"),
    DIGIT_2("2"),
    DIGIT_3("3"),
    DIGIT_4("4"),
    DIGIT_5("5"),
    DIGIT_6("6"),
    DIGIT_7("7"),
    DIGIT_8("8"),
    DIGIT_9("9"),
    ADD("+"),
    SUBTRACT("-"),
    MULTIPLY("x"),
    DIVIDE("/"),
    PERCENT("%"),
    DOT(","),
    PI("π"),
    POWER("^"),
    FACTORIAL("!"),
    SQRT("√"),
    PARENTHESIS("()")
}

sealed interface CalculatorState {
    data object Initial : CalculatorState
    data class Input(
        val expression: String,
        val result: String
    ) : CalculatorState

    data class Success(val result: String) : CalculatorState
    data class Error(val expression: String) : CalculatorState
}

