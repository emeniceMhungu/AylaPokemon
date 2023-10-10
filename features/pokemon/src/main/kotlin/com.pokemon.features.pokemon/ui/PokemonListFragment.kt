import android.os.Bundle
import android.view.View
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment

class PokemonListFragment : Fragment() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val composeView = ComposeView(requireContext())
        composeView.setContent {
            MyComposableContent()
        }
        view.addView(composeView)
    }
}

@Composable
fun MyComposableContent() {

}