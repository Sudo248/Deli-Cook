package com.sudo248.shopping_food.ui.activity.main.fragment.shopping_list

import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.sudo248.base_android.base.BaseFragment
import com.sudo248.base_android.utils.DialogUtils
import com.sudo248.shopping_food.databinding.FragmentShoppingBinding
import com.sudo248.shopping_food.ui.activity.main.adapter.OrderAdapter
import com.sudo248.shopping_food.ui.utils.Utils
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ShoppingListFragment : BaseFragment<FragmentShoppingBinding, ShoppingListViewModel>() {
    override val viewModel: ShoppingListViewModel by viewModels()
    private val args: ShoppingListFragmentArgs by navArgs()

    override val enableStateScreen: Boolean
        get() = true

    private lateinit var orderId: String

    private val adapter: OrderAdapter by lazy {
        OrderAdapter(
            onChangeAmount = {
                viewModel.updateOrder(orderId, it)
            },
            onDeleteItem = {
                viewModel.deleteOrderIngredient(orderId, it)
            }
        )
    }

    override fun initView() {
        if (args.orderId != null) {
            viewModel.getOrder(args.orderId!!)
        } else {
            viewModel.getActiveOrder()
        }
        binding.rcvIngredient.adapter = adapter
        binding.txtPayNow.isEnabled = true
        binding.txtPayNow.setOnClickListener {
            viewModel.payOrder(orderId) {
                navigateOffAll(ShoppingListFragmentDirections.actionShoppingListFragmentToHomeFragment())
            }
        }
        binding.imgBack.setOnClickListener {
            back()
        }
    }

    override fun observer() {
        super.observer()
        viewModel.order.observe(viewLifecycleOwner){
            orderId = it.orderId!!
            adapter.submitList(it.ingredients)
            binding.apply {
                txtTotalPrice.text = Utils.formatCurrency(it.totalPrice)
                txtDeliveryPrice.text = Utils.formatCurrency(it.delivery)
                txtTaxesPrice.text = Utils.formatCurrency(it.taxes)
            }
        }
    }

    override fun onStateError() {
        super.onStateError()
        binding.txtPayNow.isEnabled = false
        viewModel.error?.run {
            val message = getValueIfNotHandled()
            if (!message.isNullOrEmpty()) {
                DialogUtils.showDialog(
                    context = requireContext(),
                    title = "Error",
                    description = message
                )
            }
        }
    }
}