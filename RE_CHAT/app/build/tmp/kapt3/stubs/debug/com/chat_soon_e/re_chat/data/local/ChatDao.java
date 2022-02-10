package com.chat_soon_e.re_chat.data.local;

import java.lang.System;

@androidx.room.Dao()
@kotlin.Metadata(mv = {1, 6, 0}, k = 1, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\bH\'J\u0010\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\bH\'J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\'J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\bH\'J\u0016\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0013\u001a\u00020\bH\'J\u000e\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\b0\u0012H\'J\u000e\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00050\u0012H\'J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\bH\'J\u001e\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00122\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\bH\'J$\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00120\u001b2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\'J$\u0010\u001e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00120\u001b2\u0006\u0010\u001d\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\bH\'J\u001c\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001c0\u00120\u001b2\u0006\u0010\u001d\u001a\u00020\rH\'J\u001c\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00120\u001b2\u0006\u0010\u0018\u001a\u00020\rH\'J\u0010\u0010!\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0010\u0010\"\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\'J\u0018\u0010#\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010$\u001a\u00020\bH\'J\u0018\u0010%\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010&\u001a\u00020\bH\'\u00a8\u0006\'"}, d2 = {"Lcom/chat_soon_e/re_chat/data/local/ChatDao;", "", "delete", "", "chat", "Lcom/chat_soon_e/re_chat/data/entities/Chat;", "deleteByChatIdx", "chatIdx", "", "deleteOneChat", "otherUserIdx", "deleteOrgChat", "use_id", "", "groupName", "", "getChatByChatIdx", "getChatByIdx", "", "idx", "getChatIdxList", "getChatList", "getChatOtherIdx", "getFolderChat", "user_id", "folder_id", "getOneChatList", "Landroidx/lifecycle/LiveData;", "Lcom/chat_soon_e/re_chat/data/entities/ChatList;", "userIdx", "getOrgChatList", "getRecentChat", "getUserAllChat", "insert", "update", "updateFolder", "folderIdx", "updateIsNew", "status", "app_debug"})
public abstract interface ChatDao {
    
    @androidx.room.Insert()
    public abstract void insert(@org.jetbrains.annotations.NotNull()
    com.chat_soon_e.re_chat.data.entities.Chat chat);
    
    @androidx.room.Update()
    public abstract void update(@org.jetbrains.annotations.NotNull()
    com.chat_soon_e.re_chat.data.entities.Chat chat);
    
    @androidx.room.Delete()
    public abstract void delete(@org.jetbrains.annotations.NotNull()
    com.chat_soon_e.re_chat.data.entities.Chat chat);
    
    @androidx.room.Query(value = "DELETE FROM ChatTable WHERE chatIdx = :chatIdx")
    public abstract void deleteByChatIdx(int chatIdx);
    
    @androidx.room.Query(value = "DELETE FROM ChatTable WHERE OtherUserIdx= :otherUserIdx AND groupName is \'null\'")
    public abstract void deleteOneChat(int otherUserIdx);
    
    @androidx.room.Query(value = "DELETE FROM ChatTable WHERE groupName = :groupName AND otherUserIdx IN (SELECT CD.otherUserIdx FROM (SELECT C.otherUserIdx fROM ChatTable C INNER JOIN OtherUserTable OU ON C.otherUserIdx=OU.otherUserIdx WHERE OU.kakaoUserIdx = :use_id AND C.groupName = :groupName) CD)")
    public abstract void deleteOrgChat(long use_id, @org.jetbrains.annotations.NotNull()
    java.lang.String groupName);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ChatTable WHERE chatIdx = :chatIdx")
    public abstract com.chat_soon_e.re_chat.data.entities.Chat getChatByChatIdx(int chatIdx);
    
    @androidx.room.Query(value = "UPDATE ChatTable SET isNew= :status WHERE chatIdx= :chatIdx")
    public abstract void updateIsNew(int chatIdx, int status);
    
    @androidx.room.Query(value = "UPDATE ChatTable SET folderIdx= :folderIdx WHERE chatIdx= :chatIdx")
    public abstract void updateFolder(int chatIdx, int folderIdx);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ChatTable WHERE otherUserIdx = :idx")
    public abstract java.util.List<com.chat_soon_e.re_chat.data.entities.Chat> getChatByIdx(int idx);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT CM.chatIdx, CL.chatName AS nickName, CL.profileImg AS profileImg, CL.latestTime AS postTime, CM.message, CM.groupName, CM.isNew\nFROM\n    (SELECT (CASE WHEN C.groupName == \'null\' THEN OU.nickname ELSE C.groupName END) AS chatName,\n            (CASE WHEN C.groupName == \'null\' THEN OU.image ELSE NULL END) AS profileImg,\n            MAX(C.postTime) as latestTime\n    FROM ChatTable C INNER JOIN OtherUserTable OU on C.otherUserIdx = OU.otherUserIdx\n    WHERE OU.kakaoUserIdx = :userIdx AND C.status != \'DELETED\'\n    GROUP BY chatName, profileImg) CL\n    INNER JOIN\n    (SELECT DISTINCT (CASE WHEN C.groupName == \'null\' THEN OU.nickname ELSE C.groupName END) AS chatName, C.chatIdx, C.message, C.postTime, C.groupName, C.isNew\n    FROM ChatTable C INNER JOIN OtherUserTable OU on C.otherUserIdx = OU.otherUserIdx\n    WHERE OU.kakaoUserIdx = :userIdx AND C.status != \'DELETED\') CM\n    ON CL.chatName = CM.chatName AND CL.latestTime = CM.postTime\n ORDER BY postTime DESC;")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.chat_soon_e.re_chat.data.entities.ChatList>> getRecentChat(long userIdx);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT C.chatIdx, OU.nickname as nickName, C.groupName, OU.image as profileImg, C.message, C.postTime, C.isNew\n    FROM ChatTable AS C INNER JOIN OtherUserTable AS OU on C.otherUserIdx = OU.otherUserIdx\n    WHERE OU.kakaoUserIdx = :userIdx AND C.status != \'DELETED\' AND C.otherUserIdx IN (SELECT otherUserIdx FROM ChatTable WHERE chatIdx = :chatIdx) AND groupName is \'null\'\nORDER BY C.postTime DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.chat_soon_e.re_chat.data.entities.ChatList>> getOneChatList(long userIdx, int chatIdx);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT C.chatIdx, OU.nickname as nickName, C.groupName, OU.image as profileImg, C.message, C.postTime, C.isNew FROM ChatTable C INNER JOIN OtherUserTable OU on C.otherUserIdx = OU.otherUserIdx WHERE OU.kakaoUserIdx = :userIdx AND C.status != \'DELETED\' AND groupName = (SELECT groupName FROM ChatTable WHERE chatIdx = :chatIdx) ORDER BY C.postTime DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.chat_soon_e.re_chat.data.entities.ChatList>> getOrgChatList(long userIdx, int chatIdx);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT * FROM ChatTable")
    public abstract java.util.List<com.chat_soon_e.re_chat.data.entities.Chat> getChatList();
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT C.otherUserIdx,C.groupName,C.message,C.postTime,C.folderIdx,C.status,C.viewType,C.isChecked,C.chatIdx  FROM ChatTable C INNER JOIN OtherUserTable OU ON OU.otherUserIdx=C.otherUserIdx AND OU.kakaoUserIdx= :user_id ORDER BY C.postTime DESC")
    public abstract androidx.lifecycle.LiveData<java.util.List<com.chat_soon_e.re_chat.data.entities.Chat>> getUserAllChat(long user_id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT C.postTime, C.folderIdx, C.chatIdx, C.otherUserIdx, C.isChecked, C.message, C.groupName, C.status, C.isNew, C.viewType FROM ChatTable C INNER JOIN OtherUserTable OU ON C.otherUserIdx=OU.otherUserIdx INNER JOIN FolderContentTable FC ON C.chatIdx=FC.chatIdx INNER JOIN FolderTable F ON FC.folderIdx=F.idx WHERE OU.kakaoUserIdx= :user_id AND FC.folderIdx=:folder_id ORDER BY C.postTime DESC")
    public abstract java.util.List<com.chat_soon_e.re_chat.data.entities.Chat> getFolderChat(long user_id, int folder_id);
    
    @org.jetbrains.annotations.NotNull()
    @androidx.room.Query(value = "SELECT chatIdx FROM ChatTable")
    public abstract java.util.List<java.lang.Integer> getChatIdxList();
    
    @androidx.room.Query(value = "SELECT otherUserIdx FROM ChatTable WHERE chatIdx = :chatIdx")
    public abstract int getChatOtherIdx(int chatIdx);
}