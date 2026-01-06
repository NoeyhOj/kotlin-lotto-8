package lotto.model


class Employee(private val input: String) {
    init {
        val employees = input.split(",")
        require(employees.all{ """^(.){1,5}$""".toRegex().matches(it)}) { "[ERROR] 근무자의 별명은 1-5글자로 작성해야 합니다." }
        require(employees.all{ emp -> employees.count{ it == emp } == 1}) { "[ERROR] 근무자의 이름이 중복되면 안됩니다." }
        require(employees.size in 5..35) { "[ERROR] 고용자는 35명 이하여야 합니다." }
    }

    fun getEmployees(): List<String> = input.split(",")
}