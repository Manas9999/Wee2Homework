package com.week2assignment.homework.services;

import com.week2assignment.homework.dto.DepartmentDTO;
import com.week2assignment.homework.entity.DepartmentEntity;
import com.week2assignment.homework.exception.ResourceNotFoundException;
import com.week2assignment.homework.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    final private DepartmentRepository departmentRepository;
    final private ModelMapper modelMapper;
    public DepartmentService(DepartmentRepository departmentRepository, ModelMapper modelMapper) {
        this.departmentRepository = departmentRepository;
        this.modelMapper = modelMapper;
    }

    public List<DepartmentDTO> getAllDepartments() {
        List<DepartmentEntity> departmentEntities=departmentRepository.findAll();
        return departmentEntities
                .stream()
                .map(departmentEntity-> modelMapper.map(departmentEntity,DepartmentDTO.class))
                .collect(Collectors.toList());
    }

    public DepartmentDTO createDepartment(DepartmentDTO departmentDTO) {
        DepartmentEntity departmentEntity=modelMapper.map(departmentDTO,DepartmentEntity.class);
        DepartmentEntity savedDepartment=departmentRepository.save(departmentEntity);
        return modelMapper.map(savedDepartment,DepartmentDTO.class);
    }

    public DepartmentDTO updateDepartment(DepartmentDTO departmentdto, Long id) {
        DepartmentEntity departmentEntity=modelMapper.map(departmentdto,DepartmentEntity.class);

        if(!departmentRepository.findById(id).isPresent()){
            DepartmentEntity savedDepartment=departmentRepository.save(departmentEntity);
            return  modelMapper.map(savedDepartment,DepartmentDTO.class);
        }
        departmentEntity.setId(id);
        DepartmentEntity savedDepartment=departmentRepository.save(departmentEntity);
        return  modelMapper.map(savedDepartment,DepartmentDTO.class);

    }

    public boolean deleteDepeartmentByID(Long id) {
        if(departmentRepository.findById(id).isPresent()){
            departmentRepository.deleteById(id);
            return true;
        }
        return false;
    }
    public void isExist(Long id){
        if(!departmentRepository.existsById(id)){
           throw new ResourceNotFoundException("Resource not found");
        }
    }
    public Optional<DepartmentDTO> getDepartmentById(Long id) {
//        isExist(id);
//        Optional<DepartmentEntity> departmentEntity=departmentRepository.findById(id);
        return departmentRepository.findById(id).map(departmentEntity -> modelMapper.map(departmentEntity,DepartmentDTO.class));
    }
}
