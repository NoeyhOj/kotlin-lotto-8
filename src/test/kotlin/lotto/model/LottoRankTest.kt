package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class LottoRankTest {
    @Test
    fun `겹치는 번호와 보너스 번호에 따라 올바른 등수를 반환한다`() {
        val testList = listOf(
            Pair(0, false),
            Pair(1, false),
            Pair(2, false),
            Pair(3, false),
            Pair(4, false),
            Pair(5, false),
            Pair(5, true),
            Pair(6, false),
        )

        val rankList = listOf(
            LottoRank.NONE,
            LottoRank.NONE,
            LottoRank.NONE,
            LottoRank.FIFTH,
            LottoRank.FOURTH,
            LottoRank.THIRD,
            LottoRank.SECOND,
            LottoRank.FIRST,
        )

        testList.indices.forEach { index ->
            assertThat(LottoRank.getRank(testList[index].first, testList[index].second)).isEqualTo(rankList[index])
        }
    }
}