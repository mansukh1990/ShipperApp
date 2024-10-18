package com.example.shipperinboundorder.utils

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.widget.TextView
import cn.pedant.SweetAlert.SweetAlertDialog
import com.example.shipperinboundorder.R

object Utils {


    //error message

    @SuppressLint("ResourceType")
    fun Context.showErrorDialog(
        type: Int,
        title: String,
        message: String,
        confirmText: String,
        cancelText: String? = null,
        customImageResId: Int? = null,
        titleTextColor: Int = Color.BLACK,           // Title text color
        messageTextColor: Int = Color.DKGRAY,        // Message text color
        confirmButtonColor: Int = Color.parseColor("#4CAF50"), // Confirm button color
        cancelButtonColor: Int = Color.RED,          // Cancel button color
        backgroundColor: Int = Color.WHITE,          // Background color
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        val sweetAlertDialog = SweetAlertDialog(this, type)
        sweetAlertDialog.setTitleText(title)
            .setContentText(message)
            .setConfirmText(confirmText)
            .setConfirmClickListener { dialog ->
                dialog.dismissWithAnimation()
                onConfirm?.invoke()
            }
        sweetAlertDialog.setCancelable(false)

        // Optional cancel button with callback
        if (cancelText != null) {
            sweetAlertDialog.setCancelText(cancelText)
            sweetAlertDialog.setCancelClickListener { dialog ->
                dialog.dismissWithAnimation()
                onCancel?.invoke()
            }
        }

        // Optional custom image
        customImageResId?.let { sweetAlertDialog.setCustomImage(it) }
        sweetAlertDialog.show()

        val titleTextView =
            sweetAlertDialog.findViewById<TextView>(cn.pedant.SweetAlert.R.id.title_text)
        titleTextView?.setTextColor(resources.getColor(R.color.black))

        val contextTextView =
            sweetAlertDialog.findViewById<TextView>(cn.pedant.SweetAlert.R.id.content_text)
        contextTextView?.setTextColor(resources.getColor(R.color.black))

    }

    //success message

    fun Context.showSuccessDialog(title: String, message: String) {
        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE)
        sweetAlertDialog.setTitleText(title)
        sweetAlertDialog.setContentText(message)
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()
    }


    //warning message

    fun Context.showWarningDialog(title: String, message: String, confirmText: String) {
        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        sweetAlertDialog.setTitleText(title)
        sweetAlertDialog.setContentText(message)
        sweetAlertDialog.setContentText(confirmText)
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.show()
    }

    //Bind the listener to confirm button

    fun Context.showConfirmationDialog(
        title: String,
        message: String,
        confirmText: String,
        cancelText: String? = null,
        customImageResId: Int? = null,
        onConfirm: (() -> Unit)? = null,
        onCancel: (() -> Unit)? = null
    ) {
        val sweetAlertDialog = SweetAlertDialog(this, SweetAlertDialog.WARNING_TYPE)
        sweetAlertDialog.setTitleText(title)
        sweetAlertDialog.setContentText(message)
        sweetAlertDialog.setConfirmText(confirmText)
        sweetAlertDialog.setCancelable(false)
        sweetAlertDialog.setConfirmClickListener { sDialog ->
            sDialog.dismissWithAnimation()
            onConfirm?.invoke()
        }

        if (cancelText != null) {
            sweetAlertDialog.setCancelText(cancelText)
            sweetAlertDialog.setCancelClickListener { dialog ->
                dialog.dismissWithAnimation()
                onCancel?.invoke()
            }
        }
        customImageResId?.let { sweetAlertDialog.setCustomImage(it) }

        sweetAlertDialog.show()
    }
}