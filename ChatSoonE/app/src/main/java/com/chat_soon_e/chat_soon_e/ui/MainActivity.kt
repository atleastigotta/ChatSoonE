package com.chat_soon_e.chat_soon_e.ui

import android.annotation.SuppressLint
import android.util.Log
import androidx.recyclerview.widget.DividerItemDecoration
import com.chat_soon_e.chat_soon_e.data.local.AppDatabase
import com.chat_soon_e.chat_soon_e.databinding.ActivityMainBinding
import android.content.Intent
import android.graphics.Insets
import android.graphics.Point
import android.os.Build
import android.os.Bundle
import android.view.*
import android.widget.PopupWindow
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.activity.viewModels
import java.util.*
import kotlin.collections.ArrayList
import androidx.appcompat.widget.SwitchCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.chat_soon_e.chat_soon_e.ApplicationClass.Companion.ACTIVE
import com.chat_soon_e.chat_soon_e.ApplicationClass.Companion.HIDDEN
import com.chat_soon_e.chat_soon_e.R
import com.chat_soon_e.chat_soon_e.data.entities.*
import com.chat_soon_e.chat_soon_e.databinding.ItemFolderListBinding
import com.chat_soon_e.chat_soon_e.utils.getID
import com.chat_soon_e.chat_soon_e.utils.permissionGrantred
import com.google.android.material.navigation.NavigationView
import com.skydoves.transformationlayout.TransformationAppCompatActivity
import com.skydoves.transformationlayout.onTransformationEndContainer
import com.skydoves.transformationlayout.onTransformationStartContainer

class MainActivity: NavigationView.OnNavigationItemSelectedListener, TransformationAppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var appDB: AppDatabase
    private var iconList = ArrayList<Icon>()
    private var folderList = ArrayList<Folder>()
    private var chatList = ArrayList<ChatList>()                // 데이터베이스로부터 chat list를 받아올 변수
    private lateinit var mainRVAdapter: MainRVAdapter           // chat list recycler view adpater
    private var permission: Boolean = true                      // 알림 허용 변수
    private val chatViewModel: ChatViewModel by viewModels()
    private lateinit var mPopupWindow: PopupWindow

    override fun onCreate(savedInstanceState: Bundle?) {
        onTransformationStartContainer()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        appDB = AppDatabase.getInstance(this)!!
        initIcon()                  // icon list 초기화
        initFolder()                // folder list 초기화
    }

    // initAfterBinding() 이후 실행
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()

        initRecyclerView()          // RecylcerView Adapter 연결 & 기타 설정
        initDrawerLayout()          // 설정 메뉴창 설정
        initClickListener()         // 여러 ClickListener 초기화
    }

    // 아이콘 초기화
    // 이렇게 넣어주는 방법밖에 없는 건가?
    private fun initIcon() {
        iconList = appDB.iconDao().getIconList() as ArrayList

        // 이 부분은 서버와 통신하지 않고 자체적으로 구현
        if(iconList.isEmpty()) {
            appDB.iconDao().insert(Icon(R.drawable.chatsoon01))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon02))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon03))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon04))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon05))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon06))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon06))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon07))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon08))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon09))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon10))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon11))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon12))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon13))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon14))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon15))
            appDB.iconDao().insert(Icon(R.drawable.chatsoon16))
            iconList = appDB.iconDao().getIconList() as ArrayList
        }
    }

    // 폴더 초기화
    private fun initFolder() {
        folderList = appDB.folderDao().getFolderList() as ArrayList

        // API: 전체폴더 목록 가져오기 (숨김폴더 제외)
        // 폴더 초기 세팅 (새폴더1, 새폴더2)
        if (folderList.isEmpty()) {
            appDB.folderDao().insert(Folder(0, getID(), null, "새폴더1", R.drawable.ic_baseline_folder_24, ACTIVE))
            appDB.folderDao().insert(Folder(1, getID(), null, "새폴더2", R.drawable.ic_baseline_folder_24, ACTIVE))
            folderList = appDB.folderDao().getFolderList() as ArrayList
        }
    }

    // RecyclerView
    private fun initRecyclerView() {
        // RecyclerView 구분선
        val recyclerView = binding.mainContent.mainChatListRecyclerView
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // LinearLayoutManager 설정, 새로운 데이터 추가 시 스크롤 맨 위로
        val linearLayoutManager= LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
        linearLayoutManager.stackFromEnd = true
        binding.mainContent.mainChatListRecyclerView.layoutManager = linearLayoutManager

        // RecyclerView Click Listener
        mainRVAdapter = MainRVAdapter(this, object: MainRVAdapter.MyItemClickListener {
            // 선택 모드
            override fun onChooseChatClick(view: View, position: Int) {
                //해당 item이 선택됬을 떄의 행동을 정의
                Log.d("TestPosition", mainRVAdapter.chatList[position].profileImg.toString())
                mainRVAdapter.setChecked(position)
            }

            // 일반 모드 (= 이동 모드)
            @SuppressLint("RestrictedApi")
            override fun onDefaultChatClick(view: View, position: Int, chat:ChatList) {
                val spf=this@MainActivity.getSharedPreferences("chatAll", MODE_PRIVATE)
                var editor=spf.edit()
                editor.putInt("chatAll", 1)
                editor.commit()

                var intent=Intent(this@MainActivity, ChatActivity::class.java)
                //intent.putExtra("chatListJson", chatJson)
                intent.putExtra("chatListJson", chat)
                startActivity(intent)

                mainRVAdapter.clearSelectedItemList()
//                눌렀을 경우 chatIdx의 isNew를 바꾼다.
                val database=AppDatabase.getInstance(this@MainActivity)!!
                database.chatDao().updateIsNew(chatList[position].chatIdx,1)
                database.chatListDao().updateIsNew(chatList[position].chatIdx, 1)
                Log.d("chatSelectedNew", database.chatDao().getChatByChatIdx(chatList[position].chatIdx).toString())
                Log.d("chatSelectedNewChatList", chatList[position].isNew.toString())

            }
        })

        // main chat list view model
        chatViewModel.mode.observe(this, {
            if(it == 0) {
                // 일반 모드 (= 이동 모드)
                mainRVAdapter.clearSelectedItemList()
                binding.mainContent.mainFolderIv.visibility = View.VISIBLE
                binding.mainContent.mainFolderModeIv.visibility = View.GONE
                binding.mainContent.mainCancelIv.visibility = View.GONE
                binding.mainContent.mainChatIv.visibility = View.VISIBLE
                binding.mainContent.mainDeleteIv.visibility = View.GONE
                binding.mainContent.mainMyFolderIv.visibility = View.VISIBLE
                binding.mainContent.mainBlockIv.visibility = View.GONE
            } else {
                // 선택 모드
                mainRVAdapter.clearSelectedItemList()
                binding.mainContent.mainFolderIv.visibility = View.GONE
                binding.mainContent.mainFolderModeIv.visibility = View.VISIBLE
                binding.mainContent.mainCancelIv.visibility = View.VISIBLE
                binding.mainContent.mainChatIv.visibility = View.GONE
                binding.mainContent.mainDeleteIv.visibility = View.VISIBLE
                binding.mainContent.mainMyFolderIv.visibility = View.GONE
                binding.mainContent.mainBlockIv.visibility = View.VISIBLE
            }
            // 모든 데이터의 viewType 바꿔주기
            mainRVAdapter.setViewType(currentMode = it)
        })

        // 어댑터 연결
        binding.mainContent.mainChatListRecyclerView.adapter = mainRVAdapter

        // 최근 챗 목록 데이터 추가
        appDB = AppDatabase.getInstance(this)!!
        appDB.chatDao().getRecentChat(getID()).observe(this, {
            Log.d("liveDataAdd", it.toString())
            mainRVAdapter.addItem(it)
            chatList.clear()
            chatList.addAll(it)
            binding.mainContent.mainChatListRecyclerView.scrollToPosition(mainRVAdapter.itemCount - 1)
        })

        // 취소 버튼 클릭시 다시 초기 화면으로 (폴더 선택 모드 취소)
        binding.mainContent.mainCancelIv.setOnClickListener {
            // 현재 선택 모드 -> 일반 모드로 변경
//            mainRVAdapter.removeSelectedItemList()
            mainRVAdapter.clearSelectedItemList()
            chatViewModel.setMode(mode = 0)

            binding.mainContent.mainFolderIv.visibility = View.VISIBLE
            binding.mainContent.mainFolderModeIv.visibility = View.GONE
            binding.mainContent.mainCancelIv.visibility = View.GONE
            binding.mainContent.mainBackgroundView.visibility = View.INVISIBLE
        }
    }

    // 설정 메뉴 창을 띄우는 DrawerLayout
    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    private fun initDrawerLayout() {
        binding.mainNavigationView.setNavigationItemSelectedListener(this)

        val menuItem = binding.mainNavigationView.menu.findItem(R.id.navi_setting_alarm_item)
        val drawerSwitch =
            menuItem.actionView.findViewById(R.id.main_drawer_alarm_switch) as SwitchCompat

        // 알림 권한 허용 여부에 따라 스위치(토글) 초기 상태 지정
        if (permissionGrantred(this)) {
            // 알림 권한이 허용되어 있는 경우
            drawerSwitch.toggle()
            drawerSwitch.isChecked = true
            permission = true
        } else {
            // 알림 권한이 허용되어 있지 않은 경우
            drawerSwitch.isChecked = false
            permission = false
        }

        drawerSwitch.setOnClickListener {
            if (drawerSwitch.isChecked) {
                // 알림 권한을 허용했을 때
                permission = true
                Log.d("toggleListener", "is Checked")
                    startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
                    if(permissionGrantred(this)){
                        Toast.makeText(this, "알림 권한을 허용합니다.", Toast.LENGTH_SHORT).show()
                        startForegroundService(Intent(this, MyNotificationListener::class.java))
                    }

            } else {
                // 알림 권한을 허용하지 않았을 때
                permission = false
                Log.d("toggleListener", "is not Checked")
                    startActivity(Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS"))
                    if(!permissionGrantred(this)){
                        stopService(Intent(this, MyNotificationListener::class.java))
                        Toast.makeText(this, "알림 권한을 허용하지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
            }
        }
    }

    // 설정 메뉴 창의 네비게이션 드로어의 아이템들에 대한 이벤트를 처리
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            // 설정 메뉴창의 아이템(목록)들을 클릭했을 때
            // 알림 설정
            R.id.navi_setting_alarm_item -> {
                Toast.makeText(this, "알림 설정", Toast.LENGTH_SHORT).show()
            }

            // 차단 관리
            R.id.navi_setting_block_item -> {
                Toast.makeText(this, "차단 관리", Toast.LENGTH_SHORT).show()
            }

            // 패턴 변경하기
            R.id.navi_setting_pattern_item -> {
                val lockSPF = getSharedPreferences("lock", 0)
                val pattern = lockSPF.getString("pattern", "0")

                //앱 삭제할때 같이 DB 저장 X
                // 패턴 모드 설정
                // 0: 숨긴 폴더 목록을 확인하기 위한 입력 모드
                // 1: 메인 화면의 설정창 -> 변경 모드
                // 2: 폴더 화면의 설정창 -> 변경 모드
                // 3: 메인 화면 폴더로 보내기 -> 숨김 폴더 눌렀을 경우
                val modeSPF = getSharedPreferences("mode", 0)
                val editor = modeSPF.edit()
                editor.putInt("mode", 1)
                editor.apply()

                if(pattern.equals("0")) {   // 패턴이 설정되어 있지 않은 경우 패턴 설정 페이지로
                    val intent = Intent(this@MainActivity, CreatePatternActivity::class.java)
                    startActivity(intent)
                } else {    // 패턴이 설정되어 있는 경우 입력 페이지로 (보안을 위해)
                    val intent = Intent(this@MainActivity, InputPatternActivity::class.java)
                    startActivity(intent)
                }
            }

            // 공유하기
            R.id.navi_setting_share_item -> {
                Toast.makeText(this, "공유하기", Toast.LENGTH_SHORT).show()
            }

            // 앱 리뷰하기
            R.id.navi_setting_review_item -> {
                Toast.makeText(this, "앱 리뷰하기", Toast.LENGTH_SHORT).show()
            }

            // 이메일 문의
            R.id.navi_setting_email_item -> {
                Toast.makeText(this, "이메일 문의", Toast.LENGTH_SHORT).show()
            }

            // 사용 방법 도움말
            R.id.navi_setting_helper_item -> {
                Toast.makeText(this, "사용 방법 도움말", Toast.LENGTH_SHORT).show()
            }

            // 개인정보 처리방침
            R.id.navi_setting_privacy_item -> {
                Toast.makeText(this, "개인정보 처리방침", Toast.LENGTH_SHORT).show()
            }

            else -> Toast.makeText(this, "잘못된 항목입니다.", Toast.LENGTH_SHORT).show()
        }
        return false
    }

    // 드로어가 나와있을 때 뒤로 가기 버튼을 한 경우 뒤로 가기 버튼에 대한 이벤트를 처리
    override fun onBackPressed() {
        if(binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            binding.mainDrawerLayout.closeDrawers()
            Toast.makeText(this, "뒤로 가기", Toast.LENGTH_SHORT).show()
        } else {
            super.onBackPressed()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun initClickListener() {
        // 내폴더 아이콘 클릭시 폴더 화면으로 이동
        binding.mainContent.mainMyFolderIv.setOnClickListener {
            // startNextActivityWithClear()를 사용하는 게 좋을까?

            val intent = Intent(this@MainActivity, MyFolderActivity::class.java)
            startActivity(intent)
            Log.d("toggleListener", "folder")
        }

        binding.mainContent.mainChatIv.setOnClickListener {
            // 차단 목록으로 변경될 것
        }

        // 하단 중앙 아이콘 클릭시
        binding.mainContent.mainFolderIv.setOnClickListener {
            if(chatViewModel.mode.value == 0) {
                chatViewModel.setMode(mode = 1)
            } else {
                chatViewModel.setMode(mode = 0)
            }
        }

        // 폴더 이동 선택 모드 클릭시 팝업 메뉴
        binding.mainContent.mainFolderModeIv.setOnClickListener {
            popupWindowToFolderMenu()
        }

        // 선택 모드 시
        chatViewModel.mode.observe(this,{
            if(it==1) {
                // 해당 chat 삭제
                binding.mainContent.mainDeleteIv.setOnClickListener {
                    mainRVAdapter.removeSelectedItemList()
                    Toast.makeText(this@MainActivity, "삭제하기", Toast.LENGTH_SHORT).show()
                }
                // 해당 chat 차단
                binding.mainContent.mainBlockIv.setOnClickListener {
                }
            }
        })

        // 설정 메뉴창을 여는 메뉴 아이콘 클릭시 설정 메뉴창 열리도록
        binding.mainContent.mainSettingMenuIv.setOnClickListener {
            if(!binding.mainDrawerLayout.isDrawerOpen(GravityCompat.START)) {
                // 설정 메뉴창이 닫혀있을 때
                binding.mainDrawerLayout.openDrawer(GravityCompat.START)
            }
        }

        // 설정 메뉴창에 있는 메뉴 아이콘 클릭시 설정 메뉴창 닫히도록
        val headerView = binding.mainNavigationView.getHeaderView(0)
        headerView.setOnClickListener {
            binding.mainDrawerLayout.closeDrawer(GravityCompat.START)
        }

    }

    // 폴더로 보내기 팝업 윈도우
    @SuppressLint("InflateParams")
    private fun popupWindowToFolderMenu() {
        folderList = appDB.folderDao().getFolderList() as ArrayList

        // 팝업 윈도우 사이즈를 잘못 맞추면 아이템들이 안 뜨므로 하드 코딩으로 사이즈 조정해주기
        // 아이콘 16개 (기본)
        val size = windowManager.currentWindowMetricsPointCompat()
        val width = (size.x * 0.8f).toInt()
        val height = (size.y * 0.4f).toInt()

        val inflater = getSystemService(LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupView = inflater.inflate(R.layout.popup_window_to_folder_menu, null)
        mPopupWindow = PopupWindow(popupView, width, height)

        mPopupWindow.animationStyle = -1        // 애니메이션 설정 (-1: 설정 안 함, 0: 설정)
        mPopupWindow.isFocusable = true         // 외부 영역 선택 시 팝업 윈도우 종료
        mPopupWindow.isOutsideTouchable = true
        mPopupWindow.showAtLocation(popupView, Gravity.CENTER, 0, 0)
        mPopupWindow.setOnDismissListener(PopupWindowDismissListener())
        binding.mainContent.mainBackgroundView.visibility = View.VISIBLE

        // RecyclerView 구분선
        val recyclerView = popupView.findViewById<RecyclerView>(R.id.popup_window_to_folder_menu_recycler_view)
        val dividerItemDecoration =
            DividerItemDecoration(recyclerView.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)

        // RecyclerView 초기화
        // 더미 데이터와 어댑터 연결
        val folderListRVAdapter = FolderListRVAdapter(folderList)
        recyclerView.adapter = folderListRVAdapter
        folderListRVAdapter.setMyItemClickListener(object: FolderListRVAdapter.MyItemClickListener {
            override fun onFolderClick(itemBinding: ItemFolderListBinding, itemPosition: Int) {
                // 이동하고 싶은 폴더 클릭 시 폴더로 채팅 이동 (뷰에는 그대로 남아 있도록)
                val selectedFolder = folderList[itemPosition]

                if (selectedFolder.status == HIDDEN) {
                    val lockSPF = getSharedPreferences("lock", 0)
                    val pattern = lockSPF.getString("pattern", "0")

                    // 패턴 모드 확인
                    // 0: 숨긴 폴더 목록을 확인하기 위한 입력 모드
                    // 1: 메인 화면의 설정창 -> 변경 모드
                    // 2: 폴더 화면의 설정창 -> 변경 모드
                    // 3: 메인 화면 폴더로 보내기 -> 숨김 폴더 눌렀을 경우
                    val modeSPF = getSharedPreferences("mode", 0)
                    val editor = modeSPF.edit()

                    // 여기서는 3번 모드
                    editor.putInt("mode", 3)
                    editor.apply()

                    if(pattern.equals("0")) {   // 패턴이 설정되어 있지 않은 경우 패턴 설정 페이지로
                        val intent = Intent(this@MainActivity, CreatePatternActivity::class.java)
                        startActivity(intent)
                    } else {    // 패턴이 설정되어 있는 경우 입력 페이지로 (보안을 위해)
                        val intent = Intent(this@MainActivity, InputPatternActivity::class.java)
                        startActivity(intent)
                    }
                }
                val chatDao=appDB.chatDao()
                val folderContentDao=appDB.folderContentDao()

                // 선택된 채팅의 아이디 리스트를 가져옴
                var chatIdxList=mainRVAdapter.getSelectedItem()

                Log.d("folderContents", chatIdxList.toString())
                // 폴더의 id를 가져옴

                val folderIdx=folderList[itemPosition].idx
                Log.d("MAIN-ACTIVITY", "item position: $itemPosition")
                Log.d("MAIN-ACTIVITY", "chat index list: ${chatIdxList.toString()}")
                Log.d("MAIN-ACTIVITY", "folder index: ${folderIdx.toString()}")
                //갠톡: folderIdx, otherUserIdx
                //단톡: folderIdx, userIdx, groupName

                //이동
                for(i in chatIdxList) {
                    //추후 다시 구현내용
                    val chat = chatDao.getChatByChatIdx(i)

                    //폴더에 채팅을 넣을 것 쿼리 수정해야함!

                    val otherUserIdx=chatDao.getChatOtherIdx(i)
                    if(chat.groupName!=null)
                        folderContentDao.insertOrgChat(folderIdx, getID(), chat.groupName!!)
                    else
                        folderContentDao.insertOtOChat(folderIdx, otherUserIdx)
                //folderContentDao.insert(FolderContent(folderIdx, i, ACTIVE))
                }
                Log.d("folderContents",appDB.folderContentDao().getAllfolder().toString())
                Toast.makeText(this@MainActivity, "selected folder: ${selectedFolder.folderName}", Toast.LENGTH_SHORT).show()

                // 팝업 윈도우를 꺼주는 역할
                mPopupWindow.dismiss()
                binding.mainContent.mainBackgroundView.visibility = View.INVISIBLE
            }
        })
    }

    // 디바이스 크기에 사이즈를 맞추기 위한 함수
    private fun WindowManager.currentWindowMetricsPointCompat(): Point {
        return if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
            val windowInsets = currentWindowMetrics.windowInsets
            var insets: Insets = windowInsets.getInsets(WindowInsets.Type.navigationBars())
            windowInsets.displayCutout?.run {
                insets = Insets.max(insets, Insets.of(safeInsetLeft, safeInsetTop, safeInsetRight, safeInsetBottom))
            }
            val insetsWidth = insets.right + insets.left
            val insetsHeight = insets.top + insets.bottom
            Point(currentWindowMetrics.bounds.width() - insetsWidth, currentWindowMetrics.bounds.height() - insetsHeight)
        } else{
            Point().apply {
                defaultDisplay.getSize(this)
            }
        }
    }

    inner class PopupWindowDismissListener(): PopupWindow.OnDismissListener {
        override fun onDismiss() {
            binding.mainContent.mainBackgroundView.visibility = View.INVISIBLE
        }
    }
}