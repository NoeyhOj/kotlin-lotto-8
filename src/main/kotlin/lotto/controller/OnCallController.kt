package lotto.controller

import camp.nextstep.edu.missionutils.Console
import lotto.model.Calendar
import lotto.model.Employee
import lotto.model.Info
import lotto.model.TotalSchedule
import lotto.view.InputView
import lotto.view.OutputView

class OnCallController {
    val inputView = InputView()
    val outputView = OutputView()
    fun run() {
        val calendar = getCalendar()

        showResult(calendar, getTotalSchedule(calendar))
    }

    fun getCalendar(): Calendar {
        return try {
            inputView.readDateInfo()
            val (month, day) = Console.readLine().split(",")
            Calendar(month, day)
        } catch (e: IllegalArgumentException) {
            outputView.showError(e.message.toString())
            getCalendar()
        }
    }

    fun getEmployeeList(action: () -> Unit): List<String> {
        return try {
            action()
            Employee(Console.readLine()).getEmployees()
        } catch(e: IllegalArgumentException) {
            outputView.showError(e.message.toString())
            getEmployeeList(action)
        }
    }

    fun getTotalSchedule(calendar: Calendar): List<Info> {
        return try {
            val weekdayList = getEmployeeList{ inputView.readWeekday() }
            val weekendList = getEmployeeList{ inputView.readWeekend() }
            TotalSchedule(weekdayList, weekendList, calendar.getSchedule()).getEmployeesList()
        } catch (e: IllegalArgumentException) {
            outputView.showError(e.message.toString())
            getTotalSchedule(calendar)
        }
    }

    fun showResult(calendar: Calendar, total: List<Info>) {
        total.forEach { info ->
            outputView.showSchedule(calendar.getMonth(), info.day, info.days, info.name)
        }
    }
}