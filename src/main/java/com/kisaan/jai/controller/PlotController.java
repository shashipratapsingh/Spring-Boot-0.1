package com.kisaan.jai.controller;

import com.kisaan.jai.dto.PlotDTO;
import com.kisaan.jai.payload.response.ApiResponse;
import com.kisaan.jai.payload.response.ResponseHandler;
import com.kisaan.jai.service.PlotService;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1")
@AllArgsConstructor
public class PlotController {

    private final PlotService plotService;

    @PostMapping("/plot")
    public ResponseEntity<Object> addPlot(@Valid @RequestBody PlotDTO plotDTO) {
    	PlotDTO dto=plotService.save(plotDTO);
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(dto).httpStatus(HttpStatus.CREATED).build());
    }

    @GetMapping("/plot/{plotId}")
    public ResponseEntity<Object> getPlotById(@PathVariable Long plotId) {
        PlotDTO plotDTO = plotService.findById(plotId);

        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(plotDTO).httpStatus(HttpStatus.OK).build());
    }

    @GetMapping("/plot/all")
    public ResponseEntity<Object> getAllPlot() {
        List<PlotDTO> plotList = plotService.findAll();

        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(plotList).count(plotList.size()).httpStatus(HttpStatus.OK).build());
    }

    @PutMapping("/plot/{plotId}")
    public ResponseEntity<Object> updatePlot(@PathVariable Long plotId,@Valid @RequestBody PlotDTO plotDTO) {
        PlotDTO tempPlot = plotService.update(plotId, plotDTO);
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().responseBody(tempPlot).httpStatus(HttpStatus.OK).build());
    }

    @DeleteMapping("/plot/{plotId}")
    public ResponseEntity<Object> deletePlot(@PathVariable Long plotId) {
        plotService.delete(plotId);
        return ResponseHandler.prepareResponse(
                ApiResponse.builder().message("Plot deleted successfully.").httpStatus(HttpStatus.OK).build());
    }
}
