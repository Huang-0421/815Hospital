import Vue from 'vue'
import VueRouter from 'vue-router'
import lanhu_outpay from '../views/lanhu_outpay/index.vue'
import lanhu_messagecentersystemmessagesempty from '../views/lanhu_messagecentersystemmessagesempty/index.vue'
import lanhu_personalcenter from '../views/lanhu_personalcenter/index.vue'
import lanhu_addressmanagement from '../views/lanhu_addressmanagement/index.vue'
import lanhu_addressmanagementeditor from '../views/lanhu_addressmanagementeditor/index.vue'
import lanhu_outlist from '../views/lanhu_outlist/index.vue'
import lanhu_messagecentersystemmessages from '../views/lanhu_messagecentersystemmessages/index.vue'
import lanhu_selectward from '../views/lanhu_selectward/index.vue'
import lanhu_messagecenter from '../views/lanhu_messagecenter/index.vue'
import lanhu_messagecenterservicemessagesempty from '../views/lanhu_messagecenterservicemessagesempty/index.vue'
import lanhu_addressmanagementempty from '../views/lanhu_addressmanagementempty/index.vue'
import lanhu_nursingpropangandainner2 from '../views/lanhu_nursingpropangandainner2/index.vue'
import lanhu_myclinic from '../views/lanhu_myclinic/index.vue'
import lanhu_personalinformationmodificationdoctorcard from '../views/lanhu_personalinformationmodificationdoctorcard/index.vue'
import lanhu_navigationtohospital from '../views/lanhu_navigationtohospital/index.vue'
import lanhu_welcomepage from '../views/lanhu_welcomepage/index.vue'
import lanhu_feedbackfeedbackrecord from '../views/lanhu_feedbackfeedbackrecord/index.vue'
import lanhu_signinpage from '../views/lanhu_signinpage/index.vue'
import lanhu_onlineask from '../views/lanhu_onlineask/index.vue'
import lanhu_onlineasksend from '../views/lanhu_onlineasksend/index.vue'
import lanhu_personalinformationmodificationpatientenddateselection from '../views/lanhu_personalinformationmodificationpatientenddateselection/index.vue'
import lanhu_nursingpropaganda from '../views/lanhu_nursingpropaganda/index.vue'
import lanhu_nursingpropangandainner1 from '../views/lanhu_nursingpropangandainner1/index.vue'
import lanhu_medicalknowledgeinner1 from '../views/lanhu_medicalknowledgeinner1/index.vue'
import lanhu_signuppage from '../views/lanhu_signuppage/index.vue'
import lanhu_personalinformationmodificationpatientend from '../views/lanhu_personalinformationmodificationpatientend/index.vue'
import lanhu_demstrateofhospital from '../views/lanhu_demstrateofhospital/index.vue'
import lanhu_nursingpropangandainner3 from '../views/lanhu_nursingpropangandainner3/index.vue'
import lanhu_medicalknowledge from '../views/lanhu_medicalknowledge/index.vue'
import lanhu_feedbacksubmittedsuccessfully from '../views/lanhu_feedbacksubmittedsuccessfully/index.vue'
import lanhu_addressmanagementmanage from '../views/lanhu_addressmanagementmanage/index.vue'
import lanhu_medicalknowledgeinner2 from '../views/lanhu_medicalknowledgeinner2/index.vue'
import lanhu_feedbackfeedbackrecordempty from '../views/lanhu_feedbackfeedbackrecordempty/index.vue'
import lanhu_index from '../views/lanhu_index/index.vue'
import lanhu_medicalknowledgeinner3 from '../views/lanhu_medicalknowledgeinner3/index.vue'
import lanhu_messagecenterempty from '../views/lanhu_messagecenterempty/index.vue'
import lanhu_messagecenterservicemessages from '../views/lanhu_messagecenterservicemessages/index.vue'
import lanhu_myclinicblank from '../views/lanhu_myclinicblank/index.vue'
import lanhu_bookcall from'../views/lanhu_bookcall/index.vue'
import lanhu_doctereditinfo from '../views/lanhu_doctereditinfo/index.vue'
import lanhu_doctorindex from '../views/lanhu_doctorindex/index.vue'
import lanhu_doctormessage from '../views/lanhu_doctormessage/index.vue'
import lanhu_doctormessageinner from '../views/lanhu_doctormessageinner/index.vue'
import lanhu_doctorpatientedit from '../views/lanhu_doctorpatientedit/index.vue'
import lanhu_feedback from '../views/lanhu_feedback/index.vue'
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
Vue.use(ElementUI);

Vue.use(VueRouter)

const routes = [
    {
    path: '/',
    redirect: "lanhu_welcomepage"
  },
  {
    path: '/lanhu_outpay',
    name: 'lanhu_outpay',
    component: lanhu_outpay
  },
  {
    path: '/lanhu_personalcenter',
    name: 'lanhu_personalcenter',
    component: lanhu_personalcenter
  },
  {
    path: '/lanhu_messagecentersystemmessagesempty',
    name: 'lanhu_messagecentersystemmessagesempty',
    component: lanhu_messagecentersystemmessagesempty
  },
  {
    path: '/lanhu_addressmanagement',
    name: 'lanhu_addressmanagement',
    component: lanhu_addressmanagement
  },
  {
    path: '/lanhu_addressmanagementeditor',
    name: 'lanhu_addressmanagementeditor',
    component: lanhu_addressmanagementeditor
  },
  {
    path: '/lanhu_outlist',
    name: 'lanhu_outlist',
    component: lanhu_outlist
  },
  {
    path: '/lanhu_messagecentersystemmessages',
    name: 'lanhu_messagecentersystemmessages',
    component: lanhu_messagecentersystemmessages
  },
  {
    path: '/lanhu_selectward',
    name: 'lanhu_selectward',
    component: lanhu_selectward
  },
  {
    path: '/lanhu_messagecenter',
    name: 'lanhu_messagecenter',
    component: lanhu_messagecenter
  },
  {
    path: '/lanhu_messagecenterservicemessagesempty',
    name: 'lanhu_messagecenterservicemessagesempty',
    component: lanhu_messagecenterservicemessagesempty
  },
  {
    path: '/lanhu_addressmanagementempty',
    name: 'lanhu_addressmanagementempty',
    component: lanhu_addressmanagementempty
  },
  {
    path: '/lanhu_nursingpropangandainner2',
    name: 'lanhu_nursingpropangandainner2',
    component: lanhu_nursingpropangandainner2
  },
  {
    path: '/lanhu_myclinic',
    name: 'lanhu_myclinic',
    component: lanhu_myclinic
  },
  {
    path: '/lanhu_personalinformationmodificationdoctorcard',
    name: 'lanhu_personalinformationmodificationdoctorcard',
    component: lanhu_personalinformationmodificationdoctorcard
  },
  {
    path: '/lanhu_navigationtohospital',
    name: 'lanhu_navigationtohospital',
    component: lanhu_navigationtohospital
  },
  {
    path: '/lanhu_welcomepage',
    name: 'lanhu_welcomepage',
    component: lanhu_welcomepage
  },
  {
    path: '/lanhu_feedbackfeedbackrecord',
    name: 'lanhu_feedbackfeedbackrecord',
    component: lanhu_feedbackfeedbackrecord
  },
  {
    path: '/lanhu_signinpage',
    name: 'lanhu_signinpage',
    component: lanhu_signinpage
  },
  {
    path: '/lanhu_onlineask',
    name: 'lanhu_onlineask',
    component: lanhu_onlineask
  },
  {
    path: '/lanhu_onlineasksend',
    name: 'lanhu_onlineasksend',
    component: lanhu_onlineasksend
  },
  {
    path: '/lanhu_personalinformationmodificationpatientenddateselection',
    name: 'lanhu_personalinformationmodificationpatientenddateselection',
    component: lanhu_personalinformationmodificationpatientenddateselection
  },
  {
    path: '/lanhu_nursingpropaganda',
    name: 'lanhu_nursingpropaganda',
    component: lanhu_nursingpropaganda
  },
  {
    path: '/lanhu_nursingpropangandainner1',
    name: 'lanhu_nursingpropangandainner1',
    component: lanhu_nursingpropangandainner1
  },
  {
    path: '/lanhu_medicalknowledgeinner1',
    name: 'lanhu_medicalknowledgeinner1',
    component: lanhu_medicalknowledgeinner1
  },
  {
    path: '/lanhu_signuppage',
    name: 'lanhu_signuppage',
    component: lanhu_signuppage
  },
  {
    path: '/lanhu_personalinformationmodificationpatientend',
    name: 'lanhu_personalinformationmodificationpatientend',
    component: lanhu_personalinformationmodificationpatientend
  },
  {
    path: '/lanhu_demstrateofhospital',
    name: 'lanhu_demstrateofhospital',
    component: lanhu_demstrateofhospital
  },
  {
    path: '/lanhu_nursingpropangandainner3',
    name: 'lanhu_nursingpropangandainner3',
    component: lanhu_nursingpropangandainner3
  },
  {
    path: '/lanhu_medicalknowledge',
    name: 'lanhu_medicalknowledge',
    component: lanhu_medicalknowledge
  },
  {
    path: '/lanhu_feedbacksubmittedsuccessfully',
    name: 'lanhu_feedbacksubmittedsuccessfully',
    component: lanhu_feedbacksubmittedsuccessfully
  },
  {
    path: '/lanhu_addressmanagementmanage',
    name: 'lanhu_addressmanagementmanage',
    component: lanhu_addressmanagementmanage
  },
  {
    path: '/lanhu_medicalknowledgeinner2',
    name: 'lanhu_medicalknowledgeinner2',
    component: lanhu_medicalknowledgeinner2
  },
  {
    path: '/lanhu_feedbackfeedbackrecordempty',
    name: 'lanhu_feedbackfeedbackrecordempty',
    component: lanhu_feedbackfeedbackrecordempty
  },
  {
    path: '/lanhu_index',
    name: 'lanhu_index',
    component: lanhu_index
  },
  {
    path: '/lanhu_medicalknowledgeinner3',
    name: 'lanhu_medicalknowledgeinner3',
    component: lanhu_medicalknowledgeinner3
  },
  {
    path: '/lanhu_messagecenterempty',
    name: 'lanhu_messagecenterempty',
    component: lanhu_messagecenterempty
  },
  {
    path: '/lanhu_messagecenterservicemessages',
    name: 'lanhu_messagecenterservicemessages',
    component: lanhu_messagecenterservicemessages
  },
  {
    path: '/lanhu_myclinicblank',
    name: 'lanhu_myclinicblank',
    component: lanhu_myclinicblank
  },
  {
    path: '/lanhu_bookcall',
    name: 'lanhu_bookcall',
    component: lanhu_bookcall
  },
  {
    path: '/lanhu_doctereditinfo',
    name: 'lanhu_doctereditinfo',
    component: lanhu_doctereditinfo
  },
  {
    path: '/lanhu_doctorindex',
    name: 'lanhu_doctorindex',
    component: lanhu_doctorindex
  },
  {
    path: '/lanhu_doctormessage',
    name: 'lanhu_doctormessage',
    component: lanhu_doctormessage
  },
  {
    path: '/lanhu_doctormessageinner',
    name: 'lanhu_doctormessageinner',
    component: lanhu_doctormessageinner
  },
  {
    path: '/lanhu_doctorpatientedit',
    name: 'lanhu_doctorpatientedit',
    component: lanhu_doctorpatientedit
  },
  {
    path: '/lanhu_feedback',
    name: 'lanhu_feedback',
    component: lanhu_feedback
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
