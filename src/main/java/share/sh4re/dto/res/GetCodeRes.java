package share.sh4re.dto.res;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.time.LocalDateTime;
import java.util.List;
import lombok.Getter;
import share.sh4re.domain.Assignment;
import share.sh4re.domain.Code;
import share.sh4re.domain.Comment;
import share.sh4re.domain.User;
import share.sh4re.dto.res.GetCodeRes.GetCodeResData;

public class GetCodeRes extends BaseRes<GetCodeResData> {
  public GetCodeRes(boolean ok, GetCodeResData data) {
    super(ok, data);
  }

  @Getter
  public static class GetCodeResData {

    private final Long id;
    private final Long likes;
    private final Long views;
    private final String code;
    private final String title;
    private final String description;
    private final Code.Fields field;
    @JsonIgnoreProperties({"codeList"})
    private final User user;
    private final Boolean liked;
    @JsonIgnoreProperties({"codeList"})
    private final Assignment assignment;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;
    @JsonIgnoreProperties({"code"})
    private final List<Comment> commentList;

    public GetCodeResData(Code code, Boolean liked) {
      this.id = code.getId();
      this.likes = code.getLikes();
      this.views = code.getViews();
      this.code = code.getCode();
      this.title = code.getTitle();
      this.description = code.getDescription();
      this.field = code.getField();
      this.user = code.getUser();
      this.liked = liked;
      this.assignment = code.getAssignment();
      this.createdAt = code.getCreatedAt();
      this.updatedAt = code.getUpdatedAt();
      this.commentList = code.getCommentList();
    }
  }
}
