package lotto.model

data class Info(val name: String, val day: Int, val days: String)

class TotalSchedule(private val weekdays: List<String>, private val weekends: List<String>, private val schedule: MutableList<Total>) {
    init {
        require(weekdays.toSet() == weekends.toSet()) { "[ERROR] 평일과 주말의 근무자 이름 수가 다릅니다." }
    }

    val totalSchedule = mutableListOf<Info>()



    fun getEmployeesList(): MutableList<Info> {
        var beforeName = ""
        var weekdayList = weekdays.toMutableList()
        var weekendList = weekends.toMutableList()
        var weekdayCount = 0
        var weekendCount = 0
        schedule.forEach { total ->
            if (total.holi || !total.isDays) {
                val token = checkInfo(total, beforeName, weekendList, weekendCount)
                beforeName = token.first
                weekendList = token.second
                weekendCount = token.third
            } else {
                val token = checkInfo(total, beforeName, weekdayList, weekdayCount)
                beforeName = token.first
                weekdayList = token.second
                weekdayCount = token.third
            }
        }
        return totalSchedule
    }

    fun checkInfo(total: Total, beforeName: String, employeeList: MutableList<String>, count: Int): Triple<String, MutableList<String>, Int> {
        if (beforeName == employeeList[count % employeeList.size]) {
            var cur = employeeList[count % employeeList.size]
            var aft = employeeList[count+1 % employeeList.size]
            cur = aft.also { aft = cur }
            employeeList[count % employeeList.size] = cur
            employeeList[count + 1 % employeeList.size] = aft
        }
        totalSchedule.add(Info(employeeList[count % employeeList.size], total.day, if (total.holi) total.days.dayKor+"(휴일)" else total.days.dayKor))
        return Triple(employeeList[count % employeeList.size], employeeList, count+1)
    }
}