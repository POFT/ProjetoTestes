package org.example.util;

import org.example.model.User;
import org.junit.jupiter.api.Test;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CSVUsersReaderTest {

    @Test
    public void testReadUsersFile_FileNok() {
        String invalidPath = "invalid/file/path.csv";

        assertThrows(FileNotFoundException.class, () -> {
            CSVAttractionsReader.readAttractionsFile(invalidPath);
        });

    }

    @Test
    public void testReadUsersFile_FileOk() throws FileNotFoundException {
        String path = "src/main/resources/Cesaeland_logins.csv";
        ArrayList<User> users = CSVUsersReader.readCsvFileToUsersArray(path);

        int expectedUsers = 6;
        int resultUsers = users.size();
        assertEquals(expectedUsers,resultUsers);

        User firstUser = users.get(0);
        User secondUser = users.get(1);
        User thirdUser = users.get(2);
        User fourthUser = users.get(3);
        User fifthUser = users.get(4);
        User sixthUser = users.get(5);

        assertEquals("ADMIN", firstUser.getLoginType());
        assertEquals("root", firstUser.getUsername());
        assertEquals("root", firstUser.getPassword());

        assertEquals("ENG", secondUser.getLoginType());
        assertEquals("pimentaMachado", secondUser.getUsername());
        assertEquals("domingo", secondUser.getPassword());

        assertEquals("ADMIN", thirdUser.getLoginType());
        assertEquals("it", thirdUser.getUsername());
        assertEquals("password123", thirdUser.getPassword());

        assertEquals("ADMIN", fourthUser.getLoginType());
        assertEquals("ceo", fourthUser.getUsername());
        assertEquals("cesaeland", fourthUser.getPassword());

        assertEquals("ENG", fifthUser.getLoginType());
        assertEquals("quim", fifthUser.getUsername());
        assertEquals("sireneBombeiros", fifthUser.getPassword());

        assertEquals("ENG", sixthUser.getLoginType());
        assertEquals("manutencao", sixthUser.getUsername());
        assertEquals("cesaeland", sixthUser.getPassword());

    }
}
