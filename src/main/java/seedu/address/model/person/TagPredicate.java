package seedu.address.model.person;

import seedu.address.commons.util.StringUtil;
import seedu.address.model.tag.Tag;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

/**
 * Tests that a {@code Person}'s {@code Name} matches any of the keywords given.
 */
public class TagPredicate implements Predicate<Person> {
    private final List<String> keywords;

    public TagPredicate(List<String> keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Person person) {
        Set<Tag> tags = person.getTags();
        String flattenedTags = "";
        for (Tag tag:tags) {
            flattenedTags += (tag.getRawName() + " ");
        }
        final String testTags = flattenedTags;
        return keywords.stream()
                .anyMatch(keyword -> StringUtil.containsWordIgnoreCase(testTags, keyword));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TagPredicate // instanceof handles nulls
                && keywords.equals(((TagPredicate) other).keywords)); // state check
    }

}
