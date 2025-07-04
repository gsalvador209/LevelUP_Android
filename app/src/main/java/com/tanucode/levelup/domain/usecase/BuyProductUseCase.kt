import com.tanucode.levelup.domain.model.Product
import com.tanucode.levelup.domain.usecase.GetUserUseCase
import com.tanucode.levelup.domain.usecase.PurchaseProductUseCase
import com.tanucode.levelup.domain.usecase.UpdateUserCoinsUseCase
import kotlinx.coroutines.flow.first

class BuyProductUseCase(
    private val getUser: GetUserUseCase,
    private val updateUserCoins: UpdateUserCoinsUseCase,
    private val purchase: PurchaseProductUseCase  // tu caso de uso actual
) {
    /**
     * Devuelve true si hubo saldo y se completó la compra, false si no hay monedas suficientes.
     */
    suspend operator fun invoke(product: Product): Boolean {
        val user = getUser().first()
        return if (user.goldCoins >= product.price) {
            // 1) Descontar monedas
            updateUserCoins(user.id, ((user.goldCoins - product.price).toFloat()))
            // 2) Registrar compra
            purchase(user.id, product.id)
            true
        } else {
            false
        }
    }
}
