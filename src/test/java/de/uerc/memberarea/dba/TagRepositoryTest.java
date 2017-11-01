package de.uerc.memberarea.dba;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import de.uerc.memberarea.models.Tag;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TagRepositoryTest {

    @Autowired
    private TagRepository tagRepository;

    @Before
    public void setUp() throws Exception {
        Tag tag1 = new Tag("Tag 1");
        Tag tag2 = new Tag("Tag 2");
        // save user, verify has ID value after save
        assertNull(tag1.getId());
        assertNull(tag2.getId());// null before save
        this.tagRepository.save(tag1);
        this.tagRepository.save(tag2);
        assertNotNull(tag1.getId());
        assertNotNull(tag2.getId());
    }
    
    
    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        Tag tagA = tagRepository.findByName("Tag 1");
        assertNotNull(tagA);
    }

}
