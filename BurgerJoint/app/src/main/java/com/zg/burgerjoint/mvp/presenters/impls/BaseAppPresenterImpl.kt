package com.zg.burgerjoint.mvp.presenters.impls

import com.maex.shared.mvp.presenters.BasePresenter
import com.zg.burgerjoint.mvp.presenters.AbstractBasePresenter
import com.zg.burgerjoint.mvp.views.BaseView

abstract class BaseAppPresenterImpl<V : BaseView> : AbstractBasePresenter<V>(), BasePresenter<V>
