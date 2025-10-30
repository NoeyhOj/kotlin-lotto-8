package lotto.controller


class InputController {
    fun priceParser(lottoPrice: String): Int {
        val pricePattern = Regex("^[1-9]+\\d*000$")
        require(pricePattern.matches(lottoPrice)) { "$ERROR_MESSAGE 로또 구입 금액은 1,000원으로 나누어 떨어져야 합니다." }
        return lottoPrice.toInt()
    }

    fun winningNumberParser(inputNumbers: String): List<Int> {
        val numberPattern = Regex("^(?:[1-9]|[1-3][0-9]|4[0-5])$")
        val winningNumbers = inputNumbers.replace(" ","").split(",")
        require(winningNumbers.size == 6) { "$ERROR_MESSAGE 로또 번호는 6개여야 합니다." }
        require(winningNumbers.size == winningNumbers.distinct().size) { "$ERROR_MESSAGE 로또 번호는 중복이 없어야 합니다." }
        winningNumbers.forEach { number ->
            require(numberPattern.matches(number)) { "$ERROR_MESSAGE 당첨 번호는 1~45 범위의 숫자여야 합니다." }
        }
        return winningNumbers.map{ it.toInt() }
    }

    fun bonusParser(bonusNumber: String, winningNumbers: List<Int>): Int {
        val bonusPattern = Regex("^(?:[1-9]|[1-3][0-9]|4[0-5])$")
        require(bonusPattern.matches(bonusNumber)) { "$ERROR_MESSAGE 보너스 번호는 1~45 범위의 숫자여야 합니다." }
        require(!winningNumbers.contains(bonusNumber.toInt())) { "$ERROR_MESSAGE 보너스 번호는 당첨 번호와 중복되지 않아야 합니다." }
        return bonusNumber.toInt()
    }

    companion object {
        const val ERROR_MESSAGE = "[ERROR]"
    }
}