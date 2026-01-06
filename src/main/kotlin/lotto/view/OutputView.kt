package lotto.view

class OutputView {
    fun showSchedule(month: Int, day: Int, days: String, name: String) {
        println("${month}월 ${day}일 $days $name")
    }

    fun showError(message: String) {
        println("[ERROR] $message")
    }
}