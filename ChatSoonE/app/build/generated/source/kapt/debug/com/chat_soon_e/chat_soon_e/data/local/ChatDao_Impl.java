package com.chat_soon_e.chat_soon_e.data.local;

import android.database.Cursor;
import androidx.lifecycle.LiveData;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.chat_soon_e.chat_soon_e.data.entities.Chat;
import com.chat_soon_e.chat_soon_e.data.entities.ChatList;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;

@SuppressWarnings({"unchecked", "deprecation"})
public final class ChatDao_Impl implements ChatDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Chat> __insertionAdapterOfChat;

  private final Converter __converter = new Converter();

  private final EntityDeletionOrUpdateAdapter<Chat> __deletionAdapterOfChat;

  private final EntityDeletionOrUpdateAdapter<Chat> __updateAdapterOfChat;

  private final SharedSQLiteStatement __preparedStmtOfUpdateIsNew;

  public ChatDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfChat = new EntityInsertionAdapter<Chat>(__db) {
      @Override
      public String createQuery() {
<<<<<<< HEAD
        return "INSERT OR ABORT INTO `ChatTable` (`otherUserIdx`,`groupName`,`message`,`postTime`,`folderIdx`,`status`,`viewType`,`isChecked`,`isNew`,`chatIdx`) VALUES (?,?,?,?,?,?,?,?,?,nullif(?, 0))";
=======
        return "INSERT OR ABORT INTO `ChatTable` (`user_idx`,`other_user_idx`,`groupName`,`message`,`postTime`,`folderIdx`,`status`,`viewType`,`isChecked`,`idx`) VALUES (?,?,?,?,?,?,?,?,?,nullif(?, 0))";
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chat value) {
<<<<<<< HEAD
        stmt.bindLong(1, value.getOtherUserIdx());
=======
        stmt.bindLong(1, value.getUser_idx());
        stmt.bindLong(2, value.getOther_user_idx());
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        if (value.getGroupName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGroupName());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMessage());
        }
        final Long _tmp;
        _tmp = __converter.dateToTimestamp(value.getPostTime());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        stmt.bindLong(5, value.getFolderIdx());
        if (value.getStatus() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStatus());
        }
<<<<<<< HEAD
        stmt.bindLong(7, value.getViewType());
        final int _tmp_1;
        _tmp_1 = value.isChecked() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        if (value.isNew() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.isNew());
        }
        stmt.bindLong(10, value.getChatIdx());
=======
        stmt.bindLong(8, value.getViewType());
        final int _tmp_1;
        _tmp_1 = value.isChecked() ? 1 : 0;
        stmt.bindLong(9, _tmp_1);
        stmt.bindLong(10, value.getIdx());
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      }
    };
    this.__deletionAdapterOfChat = new EntityDeletionOrUpdateAdapter<Chat>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `ChatTable` WHERE `chatIdx` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chat value) {
        stmt.bindLong(1, value.getChatIdx());
      }
    };
    this.__updateAdapterOfChat = new EntityDeletionOrUpdateAdapter<Chat>(__db) {
      @Override
      public String createQuery() {
<<<<<<< HEAD
        return "UPDATE OR ABORT `ChatTable` SET `otherUserIdx` = ?,`groupName` = ?,`message` = ?,`postTime` = ?,`folderIdx` = ?,`status` = ?,`viewType` = ?,`isChecked` = ?,`isNew` = ?,`chatIdx` = ? WHERE `chatIdx` = ?";
=======
        return "UPDATE OR ABORT `ChatTable` SET `user_idx` = ?,`other_user_idx` = ?,`groupName` = ?,`message` = ?,`postTime` = ?,`folderIdx` = ?,`status` = ?,`viewType` = ?,`isChecked` = ?,`idx` = ? WHERE `idx` = ?";
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Chat value) {
<<<<<<< HEAD
        stmt.bindLong(1, value.getOtherUserIdx());
=======
        stmt.bindLong(1, value.getUser_idx());
        stmt.bindLong(2, value.getOther_user_idx());
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        if (value.getGroupName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getGroupName());
        }
        if (value.getMessage() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getMessage());
        }
        final Long _tmp;
        _tmp = __converter.dateToTimestamp(value.getPostTime());
        if (_tmp == null) {
          stmt.bindNull(4);
        } else {
          stmt.bindLong(4, _tmp);
        }
        stmt.bindLong(5, value.getFolderIdx());
        if (value.getStatus() == null) {
          stmt.bindNull(6);
        } else {
          stmt.bindString(6, value.getStatus());
        }
        stmt.bindLong(7, value.getViewType());
        final int _tmp_1;
        _tmp_1 = value.isChecked() ? 1 : 0;
        stmt.bindLong(8, _tmp_1);
        if (value.isNew() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindLong(9, value.isNew());
        }
<<<<<<< HEAD
        stmt.bindLong(10, value.getChatIdx());
        stmt.bindLong(11, value.getChatIdx());
      }
    };
    this.__preparedStmtOfUpdateIsNew = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE ChatTable SET isNew= ? WHERE chatIdx= ?";
        return _query;
=======
        stmt.bindLong(8, value.getViewType());
        final int _tmp_1;
        _tmp_1 = value.isChecked() ? 1 : 0;
        stmt.bindLong(9, _tmp_1);
        stmt.bindLong(10, value.getIdx());
        stmt.bindLong(11, value.getIdx());
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      }
    };
  }

  @Override
  public void insert(final Chat chat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfChat.insert(chat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void delete(final Chat chat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfChat.handle(chat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void update(final Chat chat) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfChat.handle(chat);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateIsNew(final int chatIdx, final int status) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateIsNew.acquire();
    int _argIndex = 1;
    _stmt.bindLong(_argIndex, status);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, chatIdx);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateIsNew.release(_stmt);
    }
  }

  @Override
  public Chat getChatByChatIdx(final int chatIdx) {
    final String _sql = "SELECT * FROM ChatTable WHERE chatIdx = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, chatIdx);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
<<<<<<< HEAD
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "otherUserIdx");
=======
      final int _cursorIndexOfUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "user_idx");
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "other_user_idx");
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfPostTime = CursorUtil.getColumnIndexOrThrow(_cursor, "postTime");
      final int _cursorIndexOfFolderIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "folderIdx");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfIsChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "isChecked");
<<<<<<< HEAD
      final int _cursorIndexOfIsNew = CursorUtil.getColumnIndexOrThrow(_cursor, "isNew");
      final int _cursorIndexOfChatIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "chatIdx");
      final Chat _result;
      if(_cursor.moveToFirst()) {
        final int _tmpOtherUserIdx;
        _tmpOtherUserIdx = _cursor.getInt(_cursorIndexOfOtherUserIdx);
=======
      final int _cursorIndexOfIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "idx");
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final long _tmpUser_idx;
        _tmpUser_idx = _cursor.getLong(_cursorIndexOfUserIdx);
        final int _tmpOther_user_idx;
        _tmpOther_user_idx = _cursor.getInt(_cursorIndexOfOtherUserIdx);
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        final String _tmpGroupName;
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _tmpGroupName = null;
        } else {
          _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        final Date _tmpPostTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPostTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPostTime);
        }
<<<<<<< HEAD
        _tmpPostTime = __converter.fromTimestamp(_tmp);
        final int _tmpFolderIdx;
        _tmpFolderIdx = _cursor.getInt(_cursorIndexOfFolderIdx);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final int _tmpViewType;
        _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
        final boolean _tmpIsChecked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsChecked);
        _tmpIsChecked = _tmp_1 != 0;
        final Integer _tmpIsNew;
        if (_cursor.isNull(_cursorIndexOfIsNew)) {
          _tmpIsNew = null;
        } else {
          _tmpIsNew = _cursor.getInt(_cursorIndexOfIsNew);
        }
        _result = new Chat(_tmpOtherUserIdx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked,_tmpIsNew);
        final int _tmpChatIdx;
        _tmpChatIdx = _cursor.getInt(_cursorIndexOfChatIdx);
        _result.setChatIdx(_tmpChatIdx);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Chat> getChatByIdx(final int idx) {
    final String _sql = "SELECT * FROM ChatTable WHERE otherUserIdx = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, idx);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "otherUserIdx");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfPostTime = CursorUtil.getColumnIndexOrThrow(_cursor, "postTime");
      final int _cursorIndexOfFolderIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "folderIdx");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfIsChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "isChecked");
      final int _cursorIndexOfIsNew = CursorUtil.getColumnIndexOrThrow(_cursor, "isNew");
      final int _cursorIndexOfChatIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "chatIdx");
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final int _tmpOtherUserIdx;
        _tmpOtherUserIdx = _cursor.getInt(_cursorIndexOfOtherUserIdx);
        final String _tmpGroupName;
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _tmpGroupName = null;
        } else {
          _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        final Date _tmpPostTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPostTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPostTime);
        }
=======
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        _tmpPostTime = __converter.fromTimestamp(_tmp);
        final int _tmpFolderIdx;
        _tmpFolderIdx = _cursor.getInt(_cursorIndexOfFolderIdx);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final int _tmpViewType;
        _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
        final boolean _tmpIsChecked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsChecked);
        _tmpIsChecked = _tmp_1 != 0;
<<<<<<< HEAD
        final Integer _tmpIsNew;
        if (_cursor.isNull(_cursorIndexOfIsNew)) {
          _tmpIsNew = null;
        } else {
          _tmpIsNew = _cursor.getInt(_cursorIndexOfIsNew);
        }
        _item = new Chat(_tmpOtherUserIdx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked,_tmpIsNew);
        final int _tmpChatIdx;
        _tmpChatIdx = _cursor.getInt(_cursorIndexOfChatIdx);
        _item.setChatIdx(_tmpChatIdx);
=======
        _item = new Chat(_tmpUser_idx,_tmpOther_user_idx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked);
        final int _tmpIdx;
        _tmpIdx = _cursor.getInt(_cursorIndexOfIdx);
        _item.setIdx(_tmpIdx);
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public LiveData<List<ChatList>> getRecentChat(final long id) {
    final String _sql = "SELECT CL.chatName AS chat_name, CL.profileImg AS profileImg, CL.latestTime AS latest_time, CM.message AS latest_message, CL.isNew AS isNew, CL.chatIdx\n"
            + "FROM\n"
            + "(SELECT (CASE WHEN C.groupName == \"null\" THEN OU.nickname ELSE C.groupName END) AS chatName,\n"
            + "(CASE WHEN C.groupName == \"null\" THEN OU.image ELSE NULL END) AS profileImg, C.isNew AS isNew,C.chatIdx AS chatIdx, MAX(C.postTime) as latestTime\n"
            + "FROM ChatTable as C INNER JOIN OtherUserTable as OU on C.otherUserIdx = OU.otherUserIdx\n"
            + "WHERE OU.kakaoUserIdx = ? AND C.status != 'DELETED'\n"
            + "GROUP BY chatName, profileImg) AS CL\n"
            + "INNER JOIN\n"
            + "(SELECT DISTINCT (CASE WHEN C.groupName == \"null\" THEN OU.nickname ELSE C.groupName END) AS chatName, C.message, C.postTime, C.isNew, C.chatIdx\n"
            + "FROM ChatTable C INNER JOIN OtherUserTable OU on C.otherUserIdx = OU.otherUserIdx\n"
            + "WHERE OU.kakaoUserIdx = ? AND C.status != 'DELETED') CM\n"
            + "ON CL.chatName = CM.chatName AND CL.latestTime = CM.postTime AND CL.isNew=CM.isNew AND CL.chatIdx = CM.chatIdx \n"
            + "ORDER BY latest_time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    _argIndex = 2;
    _statement.bindLong(_argIndex, id);
    return __db.getInvalidationTracker().createLiveData(new String[]{"ChatTable","OtherUserTable"}, false, new Callable<List<ChatList>>() {
      @Override
      public List<ChatList> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfChatName = CursorUtil.getColumnIndexOrThrow(_cursor, "chat_name");
          final int _cursorIndexOfProfileImg = CursorUtil.getColumnIndexOrThrow(_cursor, "profileImg");
          final int _cursorIndexOfLatestTime = CursorUtil.getColumnIndexOrThrow(_cursor, "latest_time");
          final int _cursorIndexOfLatestMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "latest_message");
          final int _cursorIndexOfIsNew = CursorUtil.getColumnIndexOrThrow(_cursor, "isNew");
          final int _cursorIndexOfChatIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "chatIdx");
          final List<ChatList> _result = new ArrayList<ChatList>(_cursor.getCount());
          while(_cursor.moveToNext()) {
            final ChatList _item;
            final String _tmpChat_name;
            if (_cursor.isNull(_cursorIndexOfChatName)) {
              _tmpChat_name = null;
            } else {
              _tmpChat_name = _cursor.getString(_cursorIndexOfChatName);
            }
            final String _tmpProfileImg;
            if (_cursor.isNull(_cursorIndexOfProfileImg)) {
              _tmpProfileImg = null;
            } else {
              _tmpProfileImg = _cursor.getString(_cursorIndexOfProfileImg);
            }
            final Date _tmpLatest_time;
            final Long _tmp;
            if (_cursor.isNull(_cursorIndexOfLatestTime)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getLong(_cursorIndexOfLatestTime);
            }
            _tmpLatest_time = __converter.fromTimestamp(_tmp);
            final String _tmpLatest_message;
            if (_cursor.isNull(_cursorIndexOfLatestMessage)) {
              _tmpLatest_message = null;
            } else {
              _tmpLatest_message = _cursor.getString(_cursorIndexOfLatestMessage);
            }
            final int _tmpIsNew;
            _tmpIsNew = _cursor.getInt(_cursorIndexOfIsNew);
            final int _tmpChatIdx;
            _tmpChatIdx = _cursor.getInt(_cursorIndexOfChatIdx);
            _item = new ChatList(_tmpChatIdx,_tmpChat_name,_tmpProfileImg,_tmpLatest_time,_tmpLatest_message,_tmpIsNew,null);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public List<Chat> getChatList() {
    final String _sql = "SELECT * FROM ChatTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
<<<<<<< HEAD
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "otherUserIdx");
=======
      final int _cursorIndexOfUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "user_idx");
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "other_user_idx");
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfPostTime = CursorUtil.getColumnIndexOrThrow(_cursor, "postTime");
      final int _cursorIndexOfFolderIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "folderIdx");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfIsChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "isChecked");
<<<<<<< HEAD
      final int _cursorIndexOfIsNew = CursorUtil.getColumnIndexOrThrow(_cursor, "isNew");
      final int _cursorIndexOfChatIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "chatIdx");
=======
      final int _cursorIndexOfIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "idx");
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final int _tmpOtherUserIdx;
        _tmpOtherUserIdx = _cursor.getInt(_cursorIndexOfOtherUserIdx);
        final String _tmpGroupName;
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _tmpGroupName = null;
        } else {
          _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        final Date _tmpPostTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPostTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPostTime);
        }
<<<<<<< HEAD
=======
        _tmpPostTime = __converter.fromTimestamp(_tmp);
        final int _tmpFolderIdx;
        _tmpFolderIdx = _cursor.getInt(_cursorIndexOfFolderIdx);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final int _tmpViewType;
        _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
        final boolean _tmpIsChecked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsChecked);
        _tmpIsChecked = _tmp_1 != 0;
        _item = new Chat(_tmpUser_idx,_tmpOther_user_idx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked);
        final int _tmpIdx;
        _tmpIdx = _cursor.getInt(_cursorIndexOfIdx);
        _item.setIdx(_tmpIdx);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Chat> getChatList() {
    final String _sql = "SELECT * FROM ChatTable";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "user_idx");
      final int _cursorIndexOfOtherUserIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "other_user_idx");
      final int _cursorIndexOfGroupName = CursorUtil.getColumnIndexOrThrow(_cursor, "groupName");
      final int _cursorIndexOfMessage = CursorUtil.getColumnIndexOrThrow(_cursor, "message");
      final int _cursorIndexOfPostTime = CursorUtil.getColumnIndexOrThrow(_cursor, "postTime");
      final int _cursorIndexOfFolderIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "folderIdx");
      final int _cursorIndexOfStatus = CursorUtil.getColumnIndexOrThrow(_cursor, "status");
      final int _cursorIndexOfViewType = CursorUtil.getColumnIndexOrThrow(_cursor, "viewType");
      final int _cursorIndexOfIsChecked = CursorUtil.getColumnIndexOrThrow(_cursor, "isChecked");
      final int _cursorIndexOfIdx = CursorUtil.getColumnIndexOrThrow(_cursor, "idx");
      final List<Chat> _result = new ArrayList<Chat>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Chat _item;
        final long _tmpUser_idx;
        _tmpUser_idx = _cursor.getLong(_cursorIndexOfUserIdx);
        final int _tmpOther_user_idx;
        _tmpOther_user_idx = _cursor.getInt(_cursorIndexOfOtherUserIdx);
        final String _tmpGroupName;
        if (_cursor.isNull(_cursorIndexOfGroupName)) {
          _tmpGroupName = null;
        } else {
          _tmpGroupName = _cursor.getString(_cursorIndexOfGroupName);
        }
        final String _tmpMessage;
        if (_cursor.isNull(_cursorIndexOfMessage)) {
          _tmpMessage = null;
        } else {
          _tmpMessage = _cursor.getString(_cursorIndexOfMessage);
        }
        final Date _tmpPostTime;
        final Long _tmp;
        if (_cursor.isNull(_cursorIndexOfPostTime)) {
          _tmp = null;
        } else {
          _tmp = _cursor.getLong(_cursorIndexOfPostTime);
        }
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        _tmpPostTime = __converter.fromTimestamp(_tmp);
        final int _tmpFolderIdx;
        _tmpFolderIdx = _cursor.getInt(_cursorIndexOfFolderIdx);
        final String _tmpStatus;
        if (_cursor.isNull(_cursorIndexOfStatus)) {
          _tmpStatus = null;
        } else {
          _tmpStatus = _cursor.getString(_cursorIndexOfStatus);
        }
        final int _tmpViewType;
        _tmpViewType = _cursor.getInt(_cursorIndexOfViewType);
        final boolean _tmpIsChecked;
        final int _tmp_1;
        _tmp_1 = _cursor.getInt(_cursorIndexOfIsChecked);
        _tmpIsChecked = _tmp_1 != 0;
<<<<<<< HEAD
        final Integer _tmpIsNew;
        if (_cursor.isNull(_cursorIndexOfIsNew)) {
          _tmpIsNew = null;
        } else {
          _tmpIsNew = _cursor.getInt(_cursorIndexOfIsNew);
        }
        _item = new Chat(_tmpOtherUserIdx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked,_tmpIsNew);
        final int _tmpChatIdx;
        _tmpChatIdx = _cursor.getInt(_cursorIndexOfChatIdx);
        _item.setChatIdx(_tmpChatIdx);
=======
        _item = new Chat(_tmpUser_idx,_tmpOther_user_idx,_tmpGroupName,_tmpMessage,_tmpPostTime,_tmpFolderIdx,_tmpStatus,_tmpViewType,_tmpIsChecked);
        final int _tmpIdx;
        _tmpIdx = _cursor.getInt(_cursorIndexOfIdx);
        _item.setIdx(_tmpIdx);
>>>>>>> f3b6f2dde75ddd688d0d416880f477ca55112bdb
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
