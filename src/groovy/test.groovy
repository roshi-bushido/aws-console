import com.amazonaws.services.ec2.model.Tag

/**
 * Created by msuarez on 3/11/15.
 */
def tags = Arrays.asList(
        new Tag(key: "Name", value: "test"),
        new Tag(key: "Creation", value: "creation"),
        new Tag(key: "Due", value: "due"),
        new Tag(key: "Owner", value: "owner"),
        new Tag(key: "Type", value: "type")
)

println tags.find { it.key.toLowerCase().equals("name") }.value


