package lotto.view

import camp.nextstep.edu.missionutils.Console
import lotto.model.Lotto
import lotto.model.LottoRank

class View {
    fun showPriceInput(): String {
        println("구입금액을 입력해 주세요.")
        return Console.readLine()
    }

    fun showBuyLottoCount(lottoCount: Int) {
        println()
        println("${lottoCount}개를 구매했습니다.")
    }

    fun showBuyLotto(lottoNumbers: List<Lotto>) {
        lottoNumbers.forEach{ lotto ->
            println(lotto.getNumbers())
        }
    }

    fun showWinningNumberInput(): String {
        println()
        println("당첨 번호를 입력해 주세요.")
        return Console.readLine()
    }

    fun showBonusInput(): String {
        println()
        println("보너스 번호를 입력해 주세요.")
        return Console.readLine()
    }

    fun showResult(winningLottoList: List<LottoRank>, roi: Double) {
        // 당첨 결과
        println()
        println("당첨 통계")
        println("---")
        println("3개 일치 (5,000원) - ${winningLottoList.count{ it == LottoRank.FIFTH}}개")
        println("4개 일치 (50,000원) - ${winningLottoList.count{ it == LottoRank.FOURTH}}개")
        println("5개 일치 (1,500,000원) - ${winningLottoList.count{ it == LottoRank.THIRD}}개")
        println("5개 일치, 보너스 볼 일치 (30,000,000원) - ${winningLottoList.count{ it == LottoRank.SECOND}}개")
        println("6개 일치 (2,000,000,000원) - ${winningLottoList.count{ it == LottoRank.FIRST}}개")
        println("총 수익률은 ${String.format("%.1f", roi)}%입니다.")
        println()
    }

    fun showError(e: IllegalArgumentException) {
        println(e.message)
    }
}