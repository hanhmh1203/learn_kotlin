package keddit.com.egn.keddit.ui

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.disposables.Disposables
import io.reactivex.schedulers.Schedulers

import keddit.com.egn.keddit.R
import keddit.com.egn.keddit.base.RxBaseFragment
import keddit.com.egn.keddit.ui.adapter.NewsAdapter
import keddit.com.egn.keddit.ui.adapter.model.RedditNewsItem
import keddit.com.egn.keddit.commons.inflate
import keddit.com.egn.keddit.ui.worker.NewsManager
import kotlinx.android.synthetic.main.fragment_first.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [FirstFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 *
 */
class FirstFragment : RxBaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return container?.inflate(R.layout.fragment_first)
    }

    private val newsManager by lazy { NewsManager() }
    //    private var disposables: Disposables?=null
    private lateinit var disposable: Disposable

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        initAdapter()
        if (savedInstanceState == null) {
            disposable = newsManager.getNews().subscribeOn(Schedulers.io())
                    .subscribe({
                        (recyclerView.adapter as NewsAdapter).addNews(it)
                    }) {
                        Log.e("hanhmh1203", it.message)

                    }
//            compositeDisposable.add(disposable)
        }
    }

    fun initRecyclerView() {
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
    }

    fun initAdapter() {
        if (recyclerView.adapter == null) {
            recyclerView.adapter = NewsAdapter()
        }
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onDetach() {
        super.onDetach()
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

}
