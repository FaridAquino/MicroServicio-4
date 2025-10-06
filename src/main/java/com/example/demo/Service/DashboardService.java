package com.example.demo.Service;

import com.example.demo.DTO.TopGlobalEmailDTO;
import com.example.demo.DashboardData;
import com.example.demo.DashboardPublishedData;
import com.example.demo.Repository.DashboardDataRepository;
import com.example.demo.Repository.DashboardPublicationRepository;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class DashboardService {
    @Autowired
    private DashboardDataRepository dashboardDataRepository;
    @Autowired
    private DashboardPublicationRepository dashboardPublicationRepository;
    @Autowired
    private ModelMapper modelMapper;

    public String publishData(List<TopGlobalEmailDTO> tuplas) {
        DashboardPublishedData dataset = new DashboardPublishedData();

        System.out.println("📌 Nuevo DashboardPublishedData ID: " + dataset.getId());

        dataset = dashboardPublicationRepository.save(dataset);

        List<DashboardData> prev = new ArrayList<>();
        for (TopGlobalEmailDTO tupla : tuplas) {
            DashboardData savedData = new DashboardData();
            savedData.setAdminId(tupla.getAdminId());   
            savedData.setUsedHashTag(tupla.getUsedHashTag());
            savedData.setDatePosted(tupla.getDatePosted());
            savedData.setUsernameTiktokAccount(tupla.getUsernameTiktokAccount());
            savedData.setPostId(tupla.getPostId());
            savedData.setPostURL(tupla.getPostURL());
            savedData.setViews(tupla.getViews());
            savedData.setLikes(tupla.getLikes());
            savedData.setEngagement(tupla.getEngagement());
            savedData.setPublishedData(dataset);
            prev.add(savedData);
        }
        dashboardDataRepository.saveAll(prev);
        return "Success";
    }

    public List<TopGlobalEmailDTO> getData () {
        List<DashboardData> receive = dashboardDataRepository.findAllWithMaxPublicationId();
        List<TopGlobalEmailDTO> dtos = new ArrayList<>();
        for (DashboardData data : receive) {
            TopGlobalEmailDTO dto  = modelMapper.map(data,TopGlobalEmailDTO.class);
            dtos.add(dto);
        }
        return dtos;
    }


}
