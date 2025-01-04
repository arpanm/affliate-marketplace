import dayjs from 'dayjs';
import { IVideoPost } from 'app/shared/model/video-post.model';
import { ICompetitionPaymentToWinner } from 'app/shared/model/competition-payment-to-winner.model';
import { IPrize } from 'app/shared/model/prize.model';

export interface ICompetitionWinner {
  id?: number;
  prizeTitle?: string | null;
  citation?: string | null;
  certificateUrl?: string | null;
  selectedBy?: string | null;
  selectionReason?: string | null;
  isActive?: boolean | null;
  createdBy?: string | null;
  createdOn?: dayjs.Dayjs | null;
  updatedBy?: string | null;
  updatedOn?: dayjs.Dayjs | null;
  winningPost?: IVideoPost | null;
  paymentToWinner?: ICompetitionPaymentToWinner | null;
  competitionPrize?: IPrize | null;
}

export const defaultValue: Readonly<ICompetitionWinner> = {
  isActive: false,
};
