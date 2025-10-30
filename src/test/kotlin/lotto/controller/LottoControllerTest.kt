package lotto.controller

import camp.nextstep.edu.missionutils.test.Assertions.assertRandomUniqueNumbersInRangeTest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoControllerTest {
    @Test
    fun `1~45 범위의 무작위 숫자 6개를 정렬된 리스트로 반환한다`() {
        assertRandomUniqueNumbersInRangeTest(
            {
                assertThat(LottoController().createLotto()).isEqualTo(listOf(1,2,3,4,5,6))
            }, listOf(1,2,3,4,5,6)
        )
    }

    @Test
    fun `구매한 로또만큼 로또 번호를 생성한다`() {
        val lottoCount = 5
        val createLotto = listOf(
            listOf(8, 21, 23, 41, 42, 43),
            listOf(3, 5, 11, 16, 32, 38),
            listOf(7, 11, 16, 35, 36, 44),
            listOf(1, 8, 11, 31, 41, 42),
            listOf(13, 14, 16, 38, 42, 45)
        )

        assertRandomUniqueNumbersInRangeTest(
            {
                val buyLotto = LottoController().buyLotto(lottoCount)
                repeat(lottoCount) { index ->
                    assertThat(buyLotto[index].getNumbers()).isEqualTo(createLotto[index])
                }
            }, listOf(8, 21, 23, 41, 42, 43),
            listOf(3, 5, 11, 16, 32, 38),
            listOf(7, 11, 16, 35, 36, 44),
            listOf(1, 8, 11, 31, 41, 42),
            listOf(13, 14, 16, 38, 42, 45),
        )
    }
}