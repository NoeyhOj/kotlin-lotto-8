package lotto.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class LottoTest {
    @Test
    fun `로또 번호의 개수가 6개가 넘어가면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 6, 7))
        }
    }

    // TODO: 테스트가 통과하도록 프로덕션 코드 구현
    @Test
    fun `로또 번호에 중복된 숫자가 있으면 예외가 발생한다`() {
        assertThrows<IllegalArgumentException> {
            Lotto(listOf(1, 2, 3, 4, 5, 5))
        }
    }

    @Test
    fun`로또 번호가 중복 없이 1~45범위의 숫자 6개라면 로또 객체를 생성한다`() {
        val numbers = listOf(1,2,3,4,5,6)
        val lotto = Lotto(numbers)
        assertThat(lotto).isInstanceOf(Lotto::class.java)
    }

    // TODO: 추가 기능 구현에 따른 테스트 코드 작성
    @Test
    fun `Lotto 클래스의 numbers를 반환한다`() {
        val numbers = listOf(1,2,3,4,5,6)
        assertThat(Lotto(numbers).getNumbers()).isEqualTo(numbers)
    }

    @Test
    fun `당첨 번호와 구매한 로또의 똑같은 번호의 개수를 반환한다`() {
        val lotto = Lotto(listOf(1,2,3,4,5,6))
        val other = Lotto(listOf(1,2,3,4,5,7))

        assertThat(lotto.getCountNumbers(other)).isEqualTo(5)
    }

    @Test
    fun `보너스 번호가 구매한 로또에 있는지 확인한다`() {
        val lotto = Lotto(listOf(1,2,3,4,5,6))
        val bonusNumber = 5
        val notBonusNumber = 8

        assertTrue(lotto.getContainBonus(bonusNumber))
        assertFalse(lotto.getContainBonus(notBonusNumber))
    }
}