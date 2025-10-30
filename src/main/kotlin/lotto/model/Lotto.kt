package lotto.model

class Lotto(private val numbers: List<Int>) {
    init {
        require(numbers.size == 6) { "$ERROR_MESSAGE 로또 번호는 6개여야 합니다." }
        require(numbers.size == numbers.distinct().size) { "$ERROR_MESSAGE 로또 번호는 중복이 없어야 합니다." }
    }
    // TODO: 추가 기능 구현
    fun getNumbers(): List<Int> {
        return numbers
    }

    // 로또의 번호가 몇 개가 맞는지 확인
    fun getCountNumbers(otherLotto: Lotto): Int {
        return otherLotto.numbers.filter{ numbers.contains(it) }.size
    }

    fun getContainBonus(bonusNumber: Int): Boolean {
        return numbers.contains(bonusNumber)
    }

    companion object {
        const val ERROR_MESSAGE = "[ERROR]"
    }

}