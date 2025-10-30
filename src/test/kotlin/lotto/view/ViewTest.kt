package lotto.view


import camp.nextstep.edu.missionutils.test.NsTest
import lotto.model.LottoRank
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class ViewTest : NsTest() {
    @Test
    fun `1~5등의 결과가 당첨 번호와 같은 개수대로 나타난다`() {
        run()
        assertThat(output()).contains(
            "3개 일치 (5,000원) - 1개",
            "4개 일치 (50,000원) - 1개",
            "5개 일치 (1,500,000원) - 1개",
            "5개 일치, 보너스 볼 일치 (30,000,000원) - 1개",
            "6개 일치 (2,000,000,000원) - 1개",
            "총 수익률은 400628200.0%입니다."
        )
    }

    override fun runMain() {
        View().showResult(winningList, roi)
    }

    companion object {
        val winningList = listOf(LottoRank.FIFTH, LottoRank.FOURTH, LottoRank.THIRD, LottoRank.SECOND,
            LottoRank.FIRST)
        val roi = 400628200.0
    }
}