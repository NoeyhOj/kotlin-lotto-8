package lotto.controller


import camp.nextstep.edu.missionutils.Randoms
import lotto.model.Lotto
import lotto.model.LottoRank
import lotto.view.View
import kotlin.math.round

class LottoController(
    val inputController: InputController = InputController(),
    val view: View = View()
) {
    fun run() {
        try {
            val lottoPrice = inputController.priceParser(view.showPriceInput())
            view.showBuyLottoCount(lottoPrice / 1000)
            val lottoList = buyLotto(lottoPrice / 1000)
            view.showBuyLotto(lottoList)
            val winningLotto = Lotto(inputController.winningNumberParser(view.showWinningNumberInput()))
            val bonusNumber = inputController.bonusParser(view.showBonusInput(), winningLotto.getNumbers())
            val winningLottoList = lottoList.map { lotto ->
                LottoRank.getRank(winningLotto.getCountNumbers(lotto), lotto.getContainBonus(bonusNumber))
            }
            val roi = round(winningLottoList.sumOf { it.price }.toDouble() / lottoPrice * 1000) / 10
            view.showResult(winningLottoList, roi)
        } catch (e: IllegalArgumentException) {
            view.showError(e)
        }
    }

    fun createLotto(): List<Int> {
        return Randoms.pickUniqueNumbersInRange(1, 45, 6).sorted()
    }

    fun buyLotto(lottoCount: Int): List<Lotto> {
        val lottoList = (1..lottoCount).map {
            val lottoNumbers = createLotto()
            Lotto(lottoNumbers)
        }
        return lottoList
    }
}