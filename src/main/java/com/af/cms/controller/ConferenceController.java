package com.af.cms.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.af.cms.model.Conference;
import com.af.cms.respone.CommonResponse;
import com.af.cms.service.ConferenceService;

@RestController
@RequestMapping("/conference")
@CrossOrigin
public class ConferenceController {

    @Autowired
    public ConferenceService conferenceService;


    @PostMapping("/")
    public ResponseEntity<?> CreateConference(@RequestBody Conference conference) throws IOException {

        Conference respone = conferenceService.saveConference(conference);
        if (respone.equals(null)) {
            return ResponseEntity.ok(new CommonResponse<Conference>(false, null, respone));
        }
        return ResponseEntity.ok(new CommonResponse<Conference>(true, null, respone));

    }


    @GetMapping("/")
    public ResponseEntity<?> getAllConferences() {
        return ResponseEntity.ok(new CommonResponse<List<Conference>>(true, null, conferenceService.getAllConference()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteResearchPaperById(@PathVariable String id) {

        int result = conferenceService.deleteConference(id);
        if (result == 1) {
            return ResponseEntity.ok(new CommonResponse<Integer>(true, null, result));
        } else {
            return ResponseEntity.ok(new CommonResponse<Integer>(false, null, result));
        }

    }

    @PutMapping("/update")
    public ResponseEntity<?> updateConference(@RequestBody Conference conference) {

        int result = conferenceService.updateConference(conference);
        if (result == 1) {
            return ResponseEntity.ok(new CommonResponse<Integer>(true, null, result));
        } else {
            return ResponseEntity.ok(new CommonResponse<Integer>(false, null, result));

        }

    }


    @GetMapping("/conference/{id}")
    public ResponseEntity<?> getConferenceById(@PathVariable String id) {

        Conference conference = conferenceService.getConferenceById(id);
        return ResponseEntity.ok(new CommonResponse<Conference>(true, null, conference));

    }


}
