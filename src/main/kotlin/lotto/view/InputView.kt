package lotto.view

class InputView {
    fun readDateInfo() = println("비상 근무를 배정할 월과 시작 요일을 입력하세요>")

    fun readWeekday() = println("평일 비상 근무 순번대로 사원 닉네임을 입력하세요>")

    fun readWeekend() = println("휴일 비상 근무 순번대로 사원 닉네임을 입력하세요>")
}