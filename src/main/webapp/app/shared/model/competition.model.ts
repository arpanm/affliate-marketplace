import dayjs from 'dayjs';
import { ISponsor } from 'app/shared/model/sponsor.model';
import { CompetitionPaymentStatus } from 'app/shared/model/enumerations/competition-payment-status.model';

export interface ICompetition {
  id?: number;
  title?: string | null;
  description?: string | null;
  status?: keyof typeof CompetitionPaymentStatus | null;
  isBlocked?: boolean | null;
  blockReason?: string | null;
  blockedBy?: string | null;
  isPaused?: boolean | null;
  pauseReason?: string | null;
  pausedBy?: string | null;
  banner1Url?: string | null;
  banner2Url?: string | null;
  banner3Url?: string | null;
  startDate?: dayjs.Dayjs | null;
  endDate?: dayjs.Dayjs | null;
  landingUrl?: string | null;
  totalPrizeValue?: number | null;
  invoiceToSponsorUrl?: string | null;
  tncUrl?: string | null;
  tnc?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  sponsor?: ISponsor | null;
}

export const defaultValue: Readonly<ICompetition> = {
  isBlocked: false,
  isPaused: false,
  isActive: false,
};
