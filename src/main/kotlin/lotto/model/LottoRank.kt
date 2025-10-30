package lotto.model

enum class LottoRank(val price: Int) {
    FIRST(2000000000),
    SECOND(30000000),
    THIRD(1500000),
    FOURTH(50000),
    FIFTH(5000),
    NONE(0);

    companion object {
        fun getRank(sameCount: Int, isBonus: Boolean): LottoRank {
            return when {
                sameCount == 6 ->  FIRST
                sameCount == 5 && isBonus ->  SECOND
                sameCount == 5 ->  THIRD
                sameCount == 4 ->  FOURTH
                sameCount == 3 ->  FIFTH
                else -> NONE
            }
        }
    }
}