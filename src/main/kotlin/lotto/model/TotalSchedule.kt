package lotto.model

data class Info(val name: String, val day: Int, val days: String)

class TotalSchedule(private val weekdays: List<String>, private val weekends: List<String>, private val schedule: MutableList<Total>) {
    init {
        require(weekdays.toSet() == weekends.toSet()) { "[ERROR] 평일과 주말의 근무자 이름 수가 다릅니다." }
    }

    fun getEmployeesList(): MutableList<Info> {
        var weekdayCount = 0
        var weekendCount = 0
        val weekdays = weekdays.toMutableList()
        val weekends = weekends.toMutableList()
        var beforeName = ""
        val totalSchedule = mutableListOf<Info>()
        schedule.forEach { total ->
            if (total.holi) { // 휴일
                if (beforeName == weekends[weekendCount % weekends.size]) {
                    var cur = weekends[weekendCount % weekends.size]
                    var aft = weekends[weekendCount+1 % weekends.size]
                    cur = aft.also { aft = cur }
                    weekends[weekendCount % weekends.size] = cur
                    weekends[weekendCount+1 % weekends.size] = aft
                }
                totalSchedule.add(Info(weekends[weekendCount % weekends.size], total.day, total.days.dayKor+"(휴일)"))
                beforeName = weekends[weekendCount % weekends.size]
                weekendCount++
            } else {
                if (Days.getWeekdays().contains(total.days)) {
                    if (beforeName == weekdays[weekdayCount % weekdays.size])  {
                        var cur = weekdays[weekdayCount % weekdays.size]
                        var aft = weekdays[weekdayCount+1 % weekdays.size]
                        cur = aft.also { aft = cur }
                        weekdays[weekdayCount % weekdays.size] = cur
                        weekdays[weekdayCount+1 % weekdays.size] = aft
                    }
                    totalSchedule.add(Info(weekdays[weekdayCount % weekdays.size], total.day, total.days.dayKor))
                    beforeName = weekdays[weekdayCount % weekdays.size]
                    weekdayCount++
                } else {
                    if (beforeName == weekends[weekendCount % weekends.size]) {
                        var cur = weekends[weekendCount % weekends.size]
                        var aft = weekends[weekendCount+1 % weekends.size]
                        cur = aft.also { aft = cur }
                        weekends[weekendCount % weekends.size] = cur
                        weekends[weekendCount+1 % weekends.size] = aft
                    }
                    totalSchedule.add(Info(weekends[weekendCount % weekends.size], total.day, total.days.dayKor))
                    beforeName = weekends[weekendCount % weekends.size]
                    weekendCount++
                }
            }

        }
        return totalSchedule
    }
}