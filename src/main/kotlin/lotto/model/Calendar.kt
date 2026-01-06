package lotto.model

data class Total(val month: Int, val day: Int, val days: Days, val holi: Boolean)

enum class Holiday(val month: Int, val day: Int) {
    ONE(1, 1),
    TWO(3, 1),
    THREE(5, 5),
    FOUR(6, 6),
    FIVE(8, 15),
    SIX(10, 3),
    SEVEN(10, 9),
    EIGHT(12, 25);

    companion object {
        fun getMonthHoliday(month: Int): List<Int> {
            return Holiday.entries.filter{ it.month == month }.map{ it.day }
        }
    }
}

enum class Month(val month: Int, val lastDay: Int) {
    ONE(1, 31),
    TWO(2, 28),
    THREE(3, 31),
    FOUR(4, 30),
    FIVE(5, 31),
    SIX(6, 30),
    SEVEN(7, 31),
    EIGHT(8, 31),
    NINE(9, 30),
    TEN(10, 31),
    ELEVEN(11, 30),
    TWELVE(12, 31);

    companion object {
        fun getLastDay(month: Int): Month {
            return when(month) {
                1 -> ONE
                2 -> TWO
                3 -> THREE
                4 -> FOUR
                5 -> FIVE
                6 -> SIX
                7 -> SEVEN
                8 -> EIGHT
                9 -> NINE
                10 -> TEN
                11 -> ELEVEN
                12 -> TWELVE
                else -> throw IllegalArgumentException()
            }
        }
    }
}

enum class Days(val dayKor: String) {
    SUNDAY("일"), MONDAY("월"), TUESDAY("화"), WEDNESDAY("수"), THURSDAY("목"), FRIDAY("금"), SATURDAY("토");

    companion object {
        fun getDay(day: String): Days {
            return when(day) {
                "일" -> SUNDAY
                "월" -> MONDAY
                "화" -> TUESDAY
                "수" -> WEDNESDAY
                "목" -> THURSDAY
                "금" -> FRIDAY
                "토" -> SATURDAY
                else -> throw IllegalArgumentException()
            }
        }

        fun getWeekdays(): List<Days> = listOf(MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY)
        fun getWeekends(): List<Days> = listOf(SATURDAY, SUNDAY)
    }
}

class Calendar(private val month: String, private val day: String) {
    init {
        require(month.toIntOrNull() != null) { "[ERROR] 숫자로 입력" }
        require(month.toInt() in 1..12) { "[ERROR] 1-12사이의 숫자만 입력" }
        require(day in Days.entries.map { it.dayKor }) { "[ERROR] 요일을 제대로 입력하세요." }
    }

    // 5, 화 -> 5월 1일 -> 화요일,

    fun getSchedule(): MutableList<Total> {
        val startIndex = Days.entries.indexOf(Days.getDay(day))
        val lastDay = Month.getLastDay(month.toInt()).lastDay
        val schedule = mutableListOf<Total>()
        (0 until lastDay).toList().forEach { day ->
            val holi = Holiday.getMonthHoliday(month.toInt()).contains(day+1)
            schedule.add(Total(month.toInt(), day+1, Days.entries[(day + startIndex) % 7], holi))
        }
        return schedule
    }

    fun getMonth(): Int = month.toInt()

}