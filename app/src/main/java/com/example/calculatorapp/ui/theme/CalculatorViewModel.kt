package com.example.calculatorapp.ui.theme

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import java.util.Stack


class CalculatorViewModel:ViewModel() {
    private val _uiState = MutableStateFlow(CalculatorUIState())
    val uiState : StateFlow<CalculatorUIState> = _uiState.asStateFlow()

    fun isOperator(symbol:String):Boolean{
        if(symbol=="√" || symbol=="×" || symbol=="+" || symbol=="-" || symbol=="/") return true
        return false
    }
    fun action(symbol:String){
        var flag = uiState.value.flag
        if(flag==false)  {
            _uiState.update{
                curr->curr.copy(flag = true)
            }
        }

    }
    fun getNumber(symbol:String) {
        var exp:String = uiState.value.expression
        if(uiState.value.point==true && symbol==".") return
        else if(symbol=="."){
            _uiState.update { currState->currState.copy(point = true) }
        }
        if(exp=="" || isOperator(symbol)==false){
            if(exp.length!=0){
                if(exp=="0") exp = ""
            }
            exp = exp + symbol
        }
        else if(isOperator(symbol)) {
            _uiState.update { currState->currState.copy(point = false) }
            var n:Int = exp.length
            if (n!=0 && isOperator(exp[n-1].toString())) {
                var ch = exp.toCharArray()
                ch[n-1] = symbol[0]
                exp = String(ch)
            }
            else exp = exp + symbol
        }
        _uiState.update {
                currState -> currState.copy(expression = exp)
        }
    }
    fun operation(symbol:String){
        if(symbol=="AC"){
            _uiState.update { currState->currState.copy(expression = "0") }
            _uiState.update { currState->currState.copy(special = "") }
            _uiState.update { currState->currState.copy(point = false) }
            _uiState.update { currState->currState.copy(finalAns = "0") }
        }
        else if(symbol == "="){
            var exp:String = uiState.value.expression
            var exp2 = uiState.value.flag
            if(exp[0]=='-'){
                var temp:String = "0";
                temp += exp
                exp = temp
            }

            var ans:String = evaluate(exp,exp2)
            _uiState.update { currState->currState.copy(expression = ans) }
            _uiState.update{currState->currState.copy(finalAns = exp)}
        }
        else if(symbol == "↩"){
            var exp:String = uiState.value.finalAns
//            var ans:String = evaluate(exp)
            _uiState.update { currState->currState.copy(expression = exp) }
//            _uiState.update{currState->currState.copy(finalAns = ans)}
        }
        else{
            var exp:String = uiState.value.expression
            var finalAns:String = uiState.value.finalAns
            if(exp!="" && uiState.value.point==true){
                var n = exp.length
                if(exp[n-1]=='.') _uiState.update { c->c.copy(point = false) }
            }
            if(exp!="" && exp!="0") exp = exp.dropLast(1)
            else finalAns = finalAns.dropLast(1)
            if(exp=="") exp = "0"
            _uiState.update { currState->currState.copy(expression = exp) }
            _uiState.update{currState->currState.copy(finalAns = finalAns)}
        }
    }

    fun precedence(ch:Char):Int{
        if(ch=='-') return 0
        if(ch=='+') return 1
        if(ch=='×') return 2
        return 3
    }

    fun apply(n1:Double,n2:Double,op: Char):Double{
        if(op=='×') return n1*n2
        if(op=='+') return n1+n2
//        if(op=='-') return n1-n2
        if(op=='/') return n1/n2
        else return 0.0
    }

    fun isNumber(ch:Char):Boolean{
        val ch1 = '0'
        val ch2 = '9'
        val zero = ch1.toInt()
        val nine = ch2.toInt()
        val temp = ch.toInt()
        if(temp>=zero && temp<=nine) return true
        return false
    }

    fun evaluate(exp:String,flag:Boolean):String{
        if(flag){
            _uiState.update { curr->curr.copy(special = "𝘼𝙣𝙪𝙟") }
            _uiState.update { curr->curr.copy(flag = false) }
        }
        else _uiState.update { curr->curr.copy(special = "") }
        var ans:Double = 0.0
        var valueStack = Stack<Double>()
        var opStk = Stack<Char>()
        var i = 0
        var n:Int = exp.length
        var p:Int = 1
        while(i<n){
            if(i==n-1 && !isNumber(exp[i])){
                i++
                continue
            }
            if(exp[i]=='.' || isNumber(exp[i])){
                var temp:String = ""
                while(i<n && (isNumber(exp[i]) || exp[i]=='.')){
                    temp += exp[i]
                    i++
                }
                valueStack.push((temp.toDouble())*p)
                p = 1
            }
            else{
                if(opStk.empty()){
                    if(exp[i]=='-'){
                        p = -1
                        opStk.push('+');
                    }
                    else opStk.push(exp[i])
                    i++
                }
                else{
                    var pre:Int = precedence(exp[i])
                    var preTop:Int = precedence(opStk.peek())
                    if(pre>=preTop){
                        if(exp[i]=='-'){
                            p = -1
                            opStk.push('+');
                        }
                        else opStk.push(exp[i])
                        i++
                    }
                    else{
                        var n2 = valueStack.pop()
                        var n1 = valueStack.pop()
                        var oper:Char = opStk.pop()
                        var tempAns:Double
                        if(oper=='-'){
                            tempAns = 0.0
                        }
                        else tempAns = apply(n1,n2,oper)
                        valueStack.push(tempAns)
                    }
                }
            }
        }
        while(!opStk.empty()){
            var n2 = valueStack.pop()
            var n1 = valueStack.pop()
            var oper = opStk.pop()
            var tempAns:Double = apply(n1,n2,oper)
            valueStack.push(tempAns)
        }
        ans = valueStack.pop()
        var ansInt = ans.toInt()
        if(ans/ansInt==1.0 || ans/ansInt==-1.0) return ansInt.toString()
        val number:Double = Math.round(ans * 100000.0) / 100000.0
        if(number==0.0) return "0"
        var t = number.toString()
        i = 0
        while(i<t.length){
            if(t[i]=='.'){
                _uiState.update { currState->currState.copy(point = true) }
            }
            i++
        }

        return t
    }
}


