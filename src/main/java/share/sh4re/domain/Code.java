package share.sh4re.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OrderBy;
import jakarta.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
public class Code extends Base {
    public enum Fields {
        PYTHON, WEB
    }

    @NotNull
    private Long likes;

    @NotNull
    private Long views;

    @NotNull
    @Column(length=32768)
    private String code;

    @NotNull
    private String title;

    @NotNull
    @Column(length=32768)
    private String description;

    @Enumerated(EnumType.STRING)
    @NotNull
    private Fields field;

    @NotNull
    private Long classNo;

    @ManyToOne
    @NotNull
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"codeList"})
    private User user;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "code", cascade = CascadeType.ALL)
    private List<Like> likeList = new ArrayList<>();

    @ManyToOne
    @Setter
    @JoinColumn(name = "assignment_id")
    @JsonIgnoreProperties({"codeList"})
    private Assignment assignment;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "code", cascade = CascadeType.ALL)
    @JsonIgnoreProperties({"code"})
    @OrderBy("createdAt DESC")
    private List<Comment> commentList = new ArrayList<>();

    public void update(String title, String description, String code, Fields field, Long classNo, User user){
        this.likes = 0L;
        this.views = 0L;
        this.code = code;
        this.title = title;
        this.description = description;
        this.field = field;
        this.classNo = classNo;
        this.user = user;
    }

    public void edit(String title, String description, String code){
        if(code != null) this.code = code;
        if(title != null) this.title = title;
        if(description != null) this.description = description;
    }

    public void increaseLikes() {
        this.likes++;
    }

    public void decreaseLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }
}