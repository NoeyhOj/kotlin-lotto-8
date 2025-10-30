package lotto.controller

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows

class InputControllerTest {
    @Test
    fun `구입 금액이 1,000으로 나누어 떨어지지 않거나 다른 문자를 입력하면 예외가 발생한다`() {
        val exceptList = listOf("1100", "1000j", "5000,")

        exceptList.map { except ->
            assertThrows<IllegalArgumentException> {
                InputController().priceParser(except)
            }
        }
    }

    @Test
    fun `당첨 번호를 6개 이상 입력하거나, 1~45범위 밖의 숫자를 입력하거나, 다른 문자를 입력하면 예외가 발생한다`() {
        val exceptList = listOf(
            "1,2,3,4,5,6,7",
            "1,2,3,4,5,a",
            "1,2,3,.4,5,6",
            "1,1,2,3,4,5",
        )

        exceptList.forEach { except ->
            assertThrows<IllegalArgumentException> {
                InputController().winningNumberParser(except)
            }
        }
    }

    @Test
    fun `보너스 번호가 중복되거나 1~45범위 밖의 숫자라면 예외가 발생한다`() {
        val exceptList = listOf(
            "46",
            "a",
            ".",
            "1",
        )
        val numbers = listOf(1,2,3,4,5,6)

        exceptList.forEach { except ->
            assertThrows<IllegalArgumentException> {
                InputController().bonusParser(except, numbers)
            }
        }
    }
}