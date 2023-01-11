import com.mapper.StationMapper;
import com.pojo.Station;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
public class MapperTest {

    @Autowired
    private StationMapper stationMapper;
    @Test
    public void testStationMapper()
    {
        String info = "%2311320%";
        List<Station> list = stationMapper.queryStationByMonthInfo(info);
        System.out.println(list.get(0));
    }
}
